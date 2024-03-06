package com.app.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.model.User;
import com.app.repo.UserRepository;
import com.app.util.NullUtil;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
				
		User user = userRepository.findByEmail(email);
		
		if(NullUtil.isEmpty(user)) {
			throw new UsernameNotFoundException("User Not Found with email: " + email);
		}
		 
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Arrays.asList());
	}

}
