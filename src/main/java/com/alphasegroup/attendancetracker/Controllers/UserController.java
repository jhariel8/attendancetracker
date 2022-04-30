package com.alphasegroup.attendancetracker.Controllers;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.alphasegroup.attendancetracker.DataAccess.UserRepository;
import com.alphasegroup.attendancetracker.DataAccess.UserClassRepository;
import com.alphasegroup.attendancetracker.DataAccess.ClassRepository;
import com.alphasegroup.attendancetracker.DataAccess.ClassMeetingRepository;
import com.alphasegroup.attendancetracker.DataAccess.RecordRepository;
import com.alphasegroup.attendancetracker.Models.ClassMeeting;
import com.alphasegroup.attendancetracker.Models.Section;
import com.alphasegroup.attendancetracker.Models.User;
import com.alphasegroup.attendancetracker.Models.Class;
import com.alphasegroup.attendancetracker.Models.UserClass;
import com.alphasegroup.attendancetracker.Models.Record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
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
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String homePage(
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
	public String login(
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
			
			System.out.println(user.getType());
			if(user.getType().equals("admin")){
				List<Class> classes = classRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
				List<User> teachers = userRepository.findByType("teacher");
				model.addAttribute("teachers", teachers);
				model.addAttribute("classes", classes);
			}else if(user.getType().equals("teacher")){
				List<Class> classes = classRepository.findByTeacher(user);
				Map<Class, List<ClassMeeting>> classesHashMap = new HashMap<Class, List<ClassMeeting>>();
				for(Class myClass : classes){
					classesHashMap.put(myClass, classMeetingRepository.findByMyClass(myClass));
				}
				model.addAttribute("classesHashMap", classesHashMap);
			}else if(user.getType().equals("student")){
				List<UserClass> userClasses = userClassRepository.findByUser(user);
				Map<Class, Integer> classAttendedCountMap = new HashMap<Class, Integer>();
				Map<Class, Double> classAttendedPercentMap = new HashMap<Class, Double>();
				Map<Class, Integer> classMeetingTotalMap = new HashMap<Class, Integer>();
				for(UserClass userClass : userClasses){
					Class myClass = userClass.getMyClass();
					List<ClassMeeting> classMeetings = classMeetingRepository.findByMyClass(myClass);
					Integer count = 0;
					Double percent = new Double(100);
					for(ClassMeeting classMeeting : classMeetings){
						List<Record> records = recordRepository.findByClassMeetingAndUser(classMeeting, user);
						if(records.size() > 0){
							count++;
						}
					}
					if(classMeetings.size() > 0){
						percent = 100*(new Double(count))/classMeetings.size();
					}
					classAttendedCountMap.put(myClass, count);
					classAttendedPercentMap.put(myClass, percent);
					classMeetingTotalMap.put(myClass, classMeetings.size());
				}
				
				model.addAttribute("userClasses", userClasses);
				model.addAttribute("classAttendedCountMap", classAttendedCountMap);
				model.addAttribute("classAttendedPercentMap", classAttendedPercentMap);
				model.addAttribute("classMeetingTotalMap", classMeetingTotalMap);
				
			}
		}
		return "main.html";
	}
	
	@RequestMapping(value="/logout")
	public String logout(
	Model model,
	HttpServletRequest request){
		request.getSession().setAttribute("user", null);
		return "redirect:/";
	}
}