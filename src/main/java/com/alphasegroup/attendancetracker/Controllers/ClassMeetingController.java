package com.alphasegroup.attendancetracker.Controllers;

import java.sql.Timestamp;
import java.time.Instant;

import com.alphasegroup.attendancetracker.DataAccess.ClassMeetingRepository;
import com.alphasegroup.attendancetracker.Models.ClassMeeting;
import com.alphasegroup.attendancetracker.Models.Section;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClassMeetingController {

	@Autowired
	private ClassMeetingRepository classMeetingRepository;

	@RequestMapping("/classmeeting")
	public String generateClassMeetingPage() {
		return "generateclassmeeting";
	}

	@RequestMapping("/generatemeeting")
	public String generateClassMeeting(@RequestParam(name="sectionid", required=true) String sectionid, 
		@RequestParam(name="duration", required = true) int duration, Model model) {
		
		ClassMeeting cm = new ClassMeeting();
		cm.setTimestamp(new Timestamp(Instant.now().toEpochMilli()));
		cm.setDuration(duration);

		Section s = new Section();
		s.setId(Integer.parseInt(sectionid));
		cm.setSectionId(s);

		ClassMeeting created = null;

		try {
			created = classMeetingRepository.save(cm);
		}
		catch(Exception ex) {
			model.addAttribute("error", "There was a problem creating the class meeting. Try again.");
			return "generateclassmeeting";
		}

		String uri = "https://api.qrserver.com/v1/create-qr-code/?" +
					"size=200x200&data=www.google.com";
		// URI fin = null;
		// try {
		// 	fin = new URI(uri);
		// }
		// catch(Exception e) {}

		// HttpClient http = HttpClient.newHttpClient();
		// HttpRequest httpReq = HttpRequest.newBuilder().GET().uri(fin).build();

		model.addAttribute("qrcode", uri);
		model.addAttribute("classmeeting", created);

		return "qrcodepage";
	}

}