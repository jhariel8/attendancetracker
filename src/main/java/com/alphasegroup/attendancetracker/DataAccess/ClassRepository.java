package com.alphasegroup.attendancetracker.DataAccess;

import java.util.List;
import java.util.Optional;

import com.alphasegroup.attendancetracker.Models.Class;
import com.alphasegroup.attendancetracker.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Class, Integer> {
	List<Class> findByTeacher(User teacher);
	Optional<Class> findById(Integer id);
}
