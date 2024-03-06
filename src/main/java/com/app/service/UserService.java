package com.app.service;

import java.util.List;

import com.app.model.User;

public interface UserService {
	
	public User createUser(User user);
	
	public boolean deleteUser(String id);
	public User getUser(String id);
	public User findByEmail(String email);
	public List<User> getAllUser();
	public User updateUser(String id, User user);

}
