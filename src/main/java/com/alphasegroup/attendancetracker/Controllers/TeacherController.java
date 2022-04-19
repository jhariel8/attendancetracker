package com.alphasegroup.attendancetracker.Controllers;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import com.alphasegroup.attendancetracker.DataAccess.ClassRepository;
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

	
	@RequestMapping(value="/teacher/addMeeting", method=RequestMethod.POST)
	public String generateClassMeeting(
	//@RequestParam(name="className", required=true) String className,
	//@RequestParam(name="teacherId", required=true) Integer teacherId,
	//@RequestParam(name="studentIds", required=true) String studentIds,
	Model model,
	HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		if(user==null || user.getType().equals("teacher") == false) return "redirect:/error";
		
		//do stuffs here
		return "redirect:/error";
	}
	
}