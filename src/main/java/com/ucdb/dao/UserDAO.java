package com.ucdb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ucdb.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, String>{
	
	@Query(value="select u from User u where u.email=:pemail")
	public User findByEmail(@Param("pemail") String email);

	public User save(User u);
	

}
