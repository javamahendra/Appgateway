package com.app.util;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class OTPUtil {
	
	public String generateOtp() {
		
		String numbers = "0123456789";
		Random rndm_method = new Random();
		char[] otp = new char[6];
		for (int i = 0; i < 6; i++) {
			otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
		}
		System.out.println(otp);
		return new String(otp);
	}

}
