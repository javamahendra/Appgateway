package com.app.Mapper;

import org.springframework.stereotype.Component;

import com.app.model.User;
import com.app.request.SignupRequest;

@Component
public class UserMapper {

	public User convertSignupRequestToUser(SignupRequest request) {

		User user = new User();
		user.setEmail(request.getEmail());
		user.setFirstname(request.getFirstname());
		user.setLastname(request.getLastname());
		user.setPassword(request.getPassword());
		
		return user;
	}

}
