package com.alphasegroup.attendancetracker.Controllers;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.net.URLEncoder;

import com.alphasegroup.attendancetracker.DataAccess.ClassRepository;
import com.alphasegroup.attendancetracker.DataAccess.ClassMeetingRepository;
import com.alphasegroup.attendancetracker.DataAccess.UserRepository;
import com.alphasegroup.attendancetracker.DataAccess.UserClassRepository;
import com.alphasegroup.attendancetracker.Models.ClassMeeting;
import com.alphasegroup.attendancetracker.Models.Section;
import com.alphasegroup.attendancetracker.Models.User;
import com.alphasegroup.attendancetracker.Models.UserClass;
import com.alphasegroup.attendancetracker.Models.Class;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class TeacherController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserClassRepository userClassRepository;
	@Autowired
	private ClassRepository classRepository;
	@Autowired
	private ClassMeetingRepository classMeetingRepository;

	@RequestMapping(value="/teacher/addMeeting")
	public String teacherAddMeeting(
	@RequestParam(name="classId", required=true) Integer classId,
	Model model,
	HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		model.addAttribute("user", user);
		
		if(user==null || user.getType().equals("teacher") == false) return "redirect:/error";
		
		Class myClass = classRepository.getById(classId);
		ClassMeeting newClassMeeting = new ClassMeeting();
		newClassMeeting.setMyClass(myClass);
		newClassMeeting.setTimestamp(new Timestamp(Instant.now().toEpochMilli()));
		ClassMeeting created = classMeetingRepository.save(newClassMeeting);
		
		String recordURL = URLEncoder.encode("http://127.0.0.1:8080/record?classMeetingId=" + newClassMeeting.getId());
		
		model.addAttribute("QRCodeURL", "https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=" + recordURL);
		model.addAttribute("myClass", myClass);
		model.addAttribute("component", "QR.html");
		return "main.html";
	}
	
}