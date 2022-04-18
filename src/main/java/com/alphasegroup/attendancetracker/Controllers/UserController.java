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
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String generateClassMeeting1(
	Model model,
	HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			System.out.println("No session.");
		}else{
			System.out.println("Has session.");
		}
		return setPageComponentUserType(user, model);
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String generateClassMeeting(
	@RequestParam(name="username", required=true) String username,
	@RequestParam(name="password", required=true) String password,
	Model model,
	HttpServletRequest request){
		User user = null;
		List<User> users = userRepository.findByUsernameAndPassword(username, password);
		if(users.size() > 0){
			System.out.println("Login successfully");
			user = users.get(0);
			request.getSession().setAttribute("user", user);
		}else{	
			System.out.println("Login failed!");
			model.addAttribute("error", "Incorrect username or password!");
		}
		return setPageComponentUserType(user, model);
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