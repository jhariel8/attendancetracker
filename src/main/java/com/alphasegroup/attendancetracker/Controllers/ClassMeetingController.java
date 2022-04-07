package com.alphasegroup.attendancetracker.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClassMeetingController {

	@GetMapping("/classmeeting")
	public String generateClassMeetingPage() {
		return "generateclassmeeting";
	}

}