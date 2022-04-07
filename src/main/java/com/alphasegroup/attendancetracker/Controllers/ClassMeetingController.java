package com.alphasegroup.attendancetracker.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ClassMeetingController {

	@RequestMapping("/classmeeting")
	public String generateClassMeetingPage() {
		return "generateclassmeeting";
	}

}