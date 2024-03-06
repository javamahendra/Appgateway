package com.app.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.constants.AppConstants;
import com.app.model.HeaderKeys;
import com.app.model.Otp;
import com.app.model.User;
import com.app.repo.HeaderKeysRepository;
import com.app.repo.OtpRepository;
import com.app.repo.UserRepository;
import com.app.service.UserService;
import com.app.util.NullUtil;
import com.app.util.OTPUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OTPUtil otpUtil;

	@Autowired
	private OtpRepository otpRepository;
	@Autowired
	private HeaderKeysRepository headerKeysRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User createUser(User user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User usr = userRepository.save(user);
		
		System.out.println("User Details ::"+usr);
		
		List<HeaderKeys> kys = headerKeysRepository.findAll();
		System.out.println("Header Keys ::" + kys);
		
		HeaderKeys production = new HeaderKeys(AppConstants.PRODUCTION);
		production.setUser(user);
		HeaderKeys sandbox = new HeaderKeys(AppConstants.SANDBOX);
		sandbox.setUser(user);
		List<HeaderKeys> keys = Arrays.asList(production, sandbox);
		List<HeaderKeys> kys1 = headerKeysRepository.saveAll(keys);
		System.out.println("Header Keys ::" + kys1);

		Otp otp = new Otp();
		otp.setEmail(usr.getEmail());
		otp.setUserid(usr.getId());
		otp.setOtp(otpUtil.generateOtp());
		otp = otpRepository.save(otp);
		System.out.println(otp);

		return user;
	}

	@Override
	public User updateUser(String id, User user) {

		Optional<User> dbUsr = userRepository.findById(id);

		if (dbUsr.isPresent()) {
			User usr = dbUsr.get();
			String fname = NullUtil.isNotEmpty(user.getFirstname()) ? user.getFirstname() : usr.getFirstname();
			String lname = NullUtil.isNotEmpty(user.getLastname()) ? user.getLastname() : usr.getLastname();
			String pwd = NullUtil.isNotEmpty(user.getPassword()) ? user.getPassword() : usr.getPassword();
			String email = NullUtil.isNotEmpty(user.getEmail()) ? user.getEmail() : usr.getEmail();
			String mobileno = NullUtil.isNotEmpty(user.getMobileno()) ? user.getMobileno() : usr.getMobileno();
			usr.setFirstname(fname);
			usr.setLastname(lname);
			usr.setPassword(pwd);
			usr.setEmail(email);
			usr.setMobileno(mobileno);
			userRepository.save(usr);
		}

		return null;
	}

	@Override
	public boolean deleteUser(String id) {
		Optional<User> dbUsr = userRepository.findById(id);

		if (dbUsr.isPresent()) {
			User usr = dbUsr.get();
			userRepository.delete(usr);
			return true;
		}

		return false;
	}

	@Override
	public User getUser(String id) {
		Optional<User> dbUsr = userRepository.findById(id);

		if (dbUsr.isPresent()) {
			User usr = dbUsr.get();

			return usr;
		}
		return null;
	}

	@Override
	public User findByEmail(String email) {

		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> getAllUser() {

		return userRepository.findAll();
	}

}
