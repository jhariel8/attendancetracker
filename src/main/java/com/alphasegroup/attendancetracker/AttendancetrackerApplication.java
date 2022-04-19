package com.alphasegroup.attendancetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import org.springframework.beans.factory.annotation.Autowired;
import com.alphasegroup.attendancetracker.Models.User;
import com.alphasegroup.attendancetracker.DataAccess.UserRepository;

@SpringBootApplication
@RestController
//@EnableWebMvc
public class AttendancetrackerApplication {
	
		
	public static void main(String[] args) {
		SpringApplication.run(AttendancetrackerApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/initialize")
	public String initialize() {
		userRepository.save(new User("asd", "asd", "student", "Victorya"));
		
		userRepository.save(new User("student2", "student2", "student", "Joe1"));
		userRepository.save(new User("student3", "student3", "student", "Joe2"));
		userRepository.save(new User("student4", "student4", "student", "Joe3"));
		userRepository.save(new User("student5", "student5", "student", "Joe4"));
		userRepository.save(new User("student6", "student6", "student", "Joe5"));
		
		userRepository.save(new User("teacher1", "teacher1", "teacher", "Dr. Bob1"));
		userRepository.save(new User("teacher2", "teacher2", "teacher", "Dr. Bob2"));
		userRepository.save(new User("teacher3", "teacher3", "teacher", "Dr. Bob3"));
		userRepository.save(new User("teacher4", "teacher4", "teacher", "Dr. Bob4"));
		
		userRepository.save(new User("admin1", "admin1", "admin", "Director Alice"));
		
		return "Initialized db.";
	}

}
