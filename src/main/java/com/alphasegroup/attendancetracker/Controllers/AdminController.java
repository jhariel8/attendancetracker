package com.alphasegroup.attendancetracker.Controllers;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

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
public class AdminController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserClassRepository userClassRepository;
	@Autowired
	private ClassRepository classRepository;

	
	@RequestMapping(value="/admin/addClass", method=RequestMethod.POST)
	public String generateClassMeeting(
	@RequestParam(name="className", required=true) String className,
	@RequestParam(name="teacherId", required=true) Integer teacherId,
	@RequestParam(name="studentIds", required=true) String studentIds,
	Model model,
	HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		if(user==null || user.getType() != "admin") return "redirect:/error";
		
		Class newClass = new Class();
		newClass.setName(className);
		newClass.setTeacherId(teacherId);
		classRepository.save(newClass);
		Integer classId = newClass.getId();
		String[] arrStudentIds = studentIds.split("\n");
		for (String studentId : arrStudentIds){
			System.out.println(studentId.strip());
			UserClass userClass = new UserClass(new Integer(Integer.parseInt(studentId.strip())),classId);
            userClassRepository.save(userClass);
		}
		
		
		System.out.println(className);
		System.out.println(teacherId);
		System.out.println(studentIds);
		return "redirect:/";
	}
	
	private String setPageComponentUserType(User user, Model model){
		if(user == null){
			model.addAttribute("component", "login.html");
		}else{
			model.addAttribute("user", user);
			model.addAttribute("component", "user/" + user.getType() + ".html");
			if(user.getType()=="admin"){
				List<User> teachers = userRepository.findByType("teacher");
				model.addAttribute("teachers", teachers);
			}
		}
		return "main.html";
	}
}