package com.alphasegroup.attendancetracker.DataAccess;

import java.util.List;
import java.util.Optional;

import com.alphasegroup.attendancetracker.Models.ClassMeeting;
import com.alphasegroup.attendancetracker.Models.Class;
import org.springframework.data.repository.CrudRepository;

public interface ClassMeetingRepository extends CrudRepository<ClassMeeting, Integer> {
    List<ClassMeeting> findByMyClass(Class myClass);
	Optional<ClassMeeting> findById(Integer id);
}
