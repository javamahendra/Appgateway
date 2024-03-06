package com.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.User;

public interface UserRepository extends JpaRepository<User, String>{
	
	public Optional<User> findById(String id);
	public User findByEmail(String email);
	public boolean existsByEmail(String email);

}
