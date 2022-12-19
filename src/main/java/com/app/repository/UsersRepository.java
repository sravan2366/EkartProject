package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
	
	@Modifying
	@Query("update Users u set u.name=?2, u.password=?3 where u.userName = ?1")
	void setPasswordByUserName(String userName, String name, String password);
	
	@Query("Select count(u) from Users u where u.userName=?1")
	Long findyByUserName(String userName);
	
	
	@Query("select count(u) from Users u where u.userName=?1 and u.password=?2")
	int vlaidUserNameAndPassword(String userName, String Password);
}
