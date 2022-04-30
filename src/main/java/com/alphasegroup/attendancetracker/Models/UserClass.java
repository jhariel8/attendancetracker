package com.alphasegroup.attendancetracker.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserClass{
    @Id
    @GeneratedValue
    private Integer id;
	
	@ManyToOne
    @JoinColumn(name="userId")
    private User user;
	
	@ManyToOne
    @JoinColumn(name="classId")
    private Class myClass;
	
	public UserClass(){
		
	}
	
	public UserClass(User user, Class myClass){
		this.user = user;
		this.myClass = myClass;
	}
	
	public User getUser(){
		return this.user;
	}
	
	public Class getMyClass(){
		return this.myClass;
	}
}