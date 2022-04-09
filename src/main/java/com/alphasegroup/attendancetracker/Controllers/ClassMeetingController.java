package com.alphasegroup.attendancetracker.Controllers;

import java.sql.Timestamp;
import java.time.Instant;

import com.alphasegroup.attendancetracker.Models.ClassMeeting;
import com.alphasegroup.attendancetracker.Models.Section;

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
	public String generateClassMeeting(@RequestParam(name="sectionid", required=true) String sectionid, 
		@RequestParam(name="duration", required = true) int duration) {
		
		ClassMeeting cm = new ClassMeeting();
		cm.setTimestamp(new Timestamp(Instant.now().getEpochSecond()));
		cm.setDuration(duration);

		Section s = new Section();
		s.setId(Integer.parseInt(sectionid));
		cm.setSectionId(s);

		return "generateclassmeeting";
	}

}