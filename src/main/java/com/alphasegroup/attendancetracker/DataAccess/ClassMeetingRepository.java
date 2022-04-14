package com.alphasegroup.attendancetracker.DataAccess;

import com.alphasegroup.attendancetracker.Models.ClassMeeting;

import org.springframework.data.repository.CrudRepository;

public interface ClassMeetingRepository extends CrudRepository<ClassMeeting, Integer> {
    
}
