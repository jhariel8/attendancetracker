package com.alphasegroup.attendancetracker.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserClass{
    @Id
    private Integer userId;
    private Integer classId;
	
	public UserClass(){
		
	}
	
	public UserClass(Integer userId, Integer classId){
		this.userId = userId;
		this.classId = classId;
	}
}