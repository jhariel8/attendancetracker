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
public class DashboardController {
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

	
	@RequestMapping(value="/dashboardClass")
	public String dashboardClass(
	@RequestParam(name="classId", required=true) Integer classId,
	Model model,
	HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		model.addAttribute("user", user);
		
		//if(user==null || user.getType().equals("teacher") == false) return "redirect:/error";
		
		Class myClass = classRepository.getById(classId);
		List<ClassMeeting> classMeetings = classMeetingRepository.findByMyClass(myClass);
		
		System.out.println(myClass.getStudentCount());
		if(myClass.getStudentCount() == null){
			myClass.setStudentCount(25);
			classRepository.save(myClass);
		}
		Integer studentCount = myClass.getStudentCount();
		
		
		Map<ClassMeeting, Integer> classMeetingAttendedCount = new HashMap<ClassMeeting, Integer>();
		Map<ClassMeeting, Double> classMeetingAttendedPercentage = new HashMap<ClassMeeting, Double>();
		
		for(ClassMeeting classMeeting : classMeetings){
			List<Record> records = recordRepository.findByClassMeeting(classMeeting);
			classMeetingAttendedCount.put(classMeeting, records.size());
			classMeetingAttendedPercentage.put(classMeeting, new Double(Double.valueOf(100*records.size())/studentCount));
		}

		model.addAttribute("class", myClass);
		model.addAttribute("classMeetings", classMeetings);
		model.addAttribute("studentCount", studentCount);
		model.addAttribute("classMeetingAttendedCount", classMeetingAttendedCount);
		model.addAttribute("classMeetingAttendedPercentage", classMeetingAttendedPercentage);

		model.addAttribute("component", "dashboard/class.html");
		return "main.html";
	}
	
	
	
	@RequestMapping(value="/dashboardClassMeeting")
	public String dashboardClassMeeting(
	@RequestParam(name="classId", required=true) Integer classId,
	@RequestParam(name="classMeetingId", required=true) Integer classMeetingId,
	Model model,
	HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		model.addAttribute("user", user);
		
		//if(user==null || user.getType().equals("teacher") == false) return "redirect:/error";
		
		Class myClass = classRepository.getById(classId);
		List<UserClass> userClasses = userClassRepository.findByMyClass(myClass);
		ClassMeeting classMeeting = classMeetingRepository.findById(classMeetingId).get();
		
		Map<User, String> userAttendedMap = new HashMap<User, String>();
		for(UserClass userClass : userClasses){
			System.out.println(userClass.getUser().getName());
			List<Record> records = recordRepository.findByClassMeetingAndUser(classMeeting, userClass.getUser());
			System.out.println(records.size());
			if(records.size() == 0){
				userAttendedMap.put(userClass.getUser(), "");
			}else if(records.size() > 0){
				userAttendedMap.put(userClass.getUser(), "✓");
			}
		}
	
		
		model.addAttribute("class", myClass);
		model.addAttribute("userAttendedMap", userAttendedMap);

		model.addAttribute("component", "dashboard/classMeeting.html");
		return "main.html";
	}
	
	
	
	@RequestMapping(value="/dashboardClassUser")
	public String dashboardClassUser(
	@RequestParam(name="classId", required=true) Integer classId,
	@RequestParam(name="userId", required=true) Integer userId,
	Model model,
	HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		model.addAttribute("user", user);
		
		//if(user==null || user.getType().equals("teacher") == false) return "redirect:/error";
		
		Class myClass = classRepository.getById(classId);
		User myUser = userRepository.findById(userId).get();
		
		
		System.out.println(myUser.getName());
		
		Map<ClassMeeting, String> userAttendedMap = new HashMap<ClassMeeting, String>();
		List<ClassMeeting> classMeetings = classMeetingRepository.findByMyClass(myClass);
		for(ClassMeeting classMeeting : classMeetings){
			List<Record> records = recordRepository.findByClassMeetingAndUser(classMeeting, myUser);
			if(records.size() == 0){
				userAttendedMap.put(classMeeting, "");
			}else if(records.size() > 0){
				userAttendedMap.put(classMeeting, "✓");
			}
		}
		

	
		
		model.addAttribute("class", myClass);
		model.addAttribute("myUser", myUser);
		model.addAttribute("userAttendedMap", userAttendedMap);

		model.addAttribute("component", "dashboard/classUser.html");
		return "main.html";
	}
	
}