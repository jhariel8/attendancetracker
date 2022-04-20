package com.alphasegroup.attendancetracker.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Class {
    @Id
    @GeneratedValue
    private Integer id;

	@ManyToOne
    @JoinColumn(name="teacherId")
	private User teacher;
	
    private String name;

	public Integer getId() {
        return this.id;
    }
	
	public User getTeacher() {
        return this.teacher;
    }
	
	public void setTeacher(User teacher) {
        this.teacher = teacher;
    }
	
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

}
