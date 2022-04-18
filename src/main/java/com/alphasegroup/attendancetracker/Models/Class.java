package com.alphasegroup.attendancetracker.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Class {
    @Id
    @GeneratedValue
    private Integer id;

	private Integer teacherId;
	
    private String name;

	public Integer getId() {
        return this.id;
    }
	
	public Integer getTeacherId() {
        return this.teacherId;
    }
	
	public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
	
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

}
