package com.alphasegroup.attendancetracker.Models;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ClassMeeting {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name="sectionid")
    private Section section;

    private Integer duration;

    public Timestamp getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Section getSectionId() {
        return section;
    }
    
    public void setSectionId(Section section) {
        this.section = section;
    }

    public Integer getDuration() {
        return duration;
    }
    
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getId() {
        return id;
    }
}
