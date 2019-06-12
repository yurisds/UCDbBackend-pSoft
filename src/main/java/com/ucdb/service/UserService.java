package com.ucdb.service;

import org.springframework.stereotype.Service;

import com.ucdb.dao.UserDAO;
import com.ucdb.model.User;

@Service
public class UserService {

	private final UserDAO userDao;

	public UserService(UserDAO userDao) {
		this.userDao = userDao;
	}

	public User create(User user) {
		return userDao.save(user);
	}

	public void delete(String email) {
		this.userDao.deleteById(email);
	}

	public User findByEmail(String email) {
		return this.userDao.findByEmail(email);
	}
	
	public void deleteAll() {
		this.userDao.deleteAll();
	}
}
