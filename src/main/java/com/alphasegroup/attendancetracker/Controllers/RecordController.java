package com.alphasegroup.attendancetracker.Controllers;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

import com.alphasegroup.attendancetracker.DataAccess.ClassRepository;
import com.alphasegroup.attendancetracker.DataAccess.ClassMeetingRepository;
import com.alphasegroup.attendancetracker.DataAccess.UserRepository;
import com.alphasegroup.attendancetracker.DataAccess.UserClassRepository;
import com.alphasegroup.attendancetracker.DataAccess.RecordRepository;
import com.alphasegroup.attendancetracker.Models.ClassMeeting;
import com.alphasegroup.attendancetracker.Models.Section;
import com.alphasegroup.attendancetracker.Models.User;
import com.alphasegroup.attendancetracker.Models.UserClass;
import com.alphasegroup.attendancetracker.Models.Class;
import com.alphasegroup.attendancetracker.Models.Record;

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
public class RecordController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserClassRepository userClassRepository;
	@Autowired
	private ClassRepository classRepository;
	@Autowired
	private ClassMeetingRepository classMeetingRepository;
	@Autowired
	private RecordRepository recordRepository;

	
	@RequestMapping(value="/record")
	public String dashboardClass(
	@RequestParam(name="classMeetingId", required=true) Integer classMeetingId,
	Model model,
	HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		model.addAttribute("user", user);
		
		if(user==null || user.getType().equals("student") == false) return "redirect:/error";
		
		ClassMeeting classMeeting = classMeetingRepository.findById(classMeetingId).get();
		
		List<Record> records = recordRepository.findByClassMeetingAndUser(classMeeting, user);
		if(records.size() == 0){
			Record record = new Record(user, classMeeting);
			recordRepository.save(record);
		}
		return "redirect:/";
	}
}