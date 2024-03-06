package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Otp;

public interface OtpRepository extends JpaRepository<Otp, String>{

}
