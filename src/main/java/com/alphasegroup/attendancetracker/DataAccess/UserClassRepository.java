package com.alphasegroup.attendancetracker.DataAccess;

import java.util.List;

import com.alphasegroup.attendancetracker.Models.UserClass;
import com.alphasegroup.attendancetracker.Models.Class;
import com.alphasegroup.attendancetracker.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserClassRepository extends JpaRepository<UserClass, Integer> {
	List<UserClass> findByMyClass(Class myClass);
	List<UserClass> findByUser(User user);
	List<UserClass> findByMyClassAndUser(Class myClass, User user);
}
