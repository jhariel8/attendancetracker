package com.alphasegroup.attendancetracker.Controllers;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import com.alphasegroup.attendancetracker.DataAccess.UserRepository;
import com.alphasegroup.attendancetracker.Models.ClassMeeting;
import com.alphasegroup.attendancetracker.Models.Section;
import com.alphasegroup.attendancetracker.Models.User;

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
public class UsersController {
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String generateClassMeeting1(
	Model model,
	HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			System.out.println("No session.");
			return "users/login.html";
		}else{
			System.out.println("Has session.");
			return redirectToUserType(user, model);
		}
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String generateClassMeeting(
	@RequestParam(name="username", required=true) String username,
	@RequestParam(name="password", required=true) String password,
	Model model,
	HttpServletRequest request){
		List<User> users = userRepository.findByUsernameAndPassword(username, password);
		if(users.size() > 0){
			System.out.println("Login successfully");
			
			User user = users.get(0);
			request.getSession().setAttribute("user", user);
			return redirectToUserType(user, model);
		}
		
		System.out.println("Login failed!");
		return "users/login.html";
	}
	
	private String redirectToUserType(User user, Model model){
		model.addAttribute("user", user);
		String userType = user.getType();
		if(userType == "student"){
			return "users/student.html";
		}else if(userType == "teacher"){
			return "users/teacher.html";
		}else if(userType == "admin"){
			return "users/admin.html";
		}
		return "";
	}
}