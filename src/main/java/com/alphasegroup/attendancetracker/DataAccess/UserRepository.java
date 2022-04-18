package com.alphasegroup.attendancetracker.DataAccess;

import java.util.List;

import com.alphasegroup.attendancetracker.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByUsernameAndPassword(String username, String password);
	List<User> findByType(String type);
}
