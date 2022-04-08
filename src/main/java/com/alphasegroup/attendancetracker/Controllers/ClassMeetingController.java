package com.alphasegroup.attendancetracker.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClassMeetingController {

	@RequestMapping("/classmeeting")
	public String generateClassMeetingPage() {
		return "generateclassmeeting";
	}

	@RequestMapping("/generatemeeting")
	public String generateClassMeeting(@RequestParam(name="classid", required=true) String classid, 
		@RequestParam(name="duration", required = true) int duration) {
		return "generateclassmeeting";
	}

}