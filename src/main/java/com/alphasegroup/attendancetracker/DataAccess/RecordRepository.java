package com.alphasegroup.attendancetracker.DataAccess;

import java.util.List;

import com.alphasegroup.attendancetracker.Models.Record;
import com.alphasegroup.attendancetracker.Models.ClassMeeting;
import com.alphasegroup.attendancetracker.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Integer> {
	List<Record> findByClassMeeting(ClassMeeting classMeeting);
	List<Record> findByClassMeetingAndUser(ClassMeeting classMeeting, User user);
}
