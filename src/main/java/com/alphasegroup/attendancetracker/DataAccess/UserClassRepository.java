package com.alphasegroup.attendancetracker.DataAccess;

import java.util.List;

import com.alphasegroup.attendancetracker.Models.UserClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserClassRepository extends JpaRepository<UserClass, Integer> {
    //List<UserClass> findByUsernameAndPassword(String username, String password);
	//List<UserClass> findByType(String type);
}
