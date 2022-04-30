package com.alphasegroup.attendancetracker.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Record{
    @Id
    @GeneratedValue
    private Integer id;
	
	@ManyToOne
    @JoinColumn(name="classMeetingId")
    private ClassMeeting classMeeting;
	
	@ManyToOne
    @JoinColumn(name="userId")
    private User user;
	
	public Record(){
		
	}
	
	public Record(User user, ClassMeeting classMeeting){
		this.user = user;
		this.classMeeting = classMeeting;
	}
}