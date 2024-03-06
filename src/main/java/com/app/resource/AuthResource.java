package com.app.resource;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Mapper.UserMapper;
import com.app.constants.AppConstants;
import com.app.model.User;
import com.app.request.LoginRequest;
import com.app.request.SignupRequest;
import com.app.response.Error;
import com.app.response.Response;
import com.app.service.UserService;
import com.app.util.JwtUtils;
import com.app.util.NullUtil;
import com.app.validator.JSONValdiator;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/auth")
public class AuthResource {

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserMapper userMapper;

	@Value("${Generate_Token_v1_0_SCHEMA}")
	private String generateTokenSchema;

	@PostMapping(path = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response generateToken(@RequestHeader Map<String, String> header, String jsonData) {

		JSONValdiator jsonValdiator = new JSONValdiator();
		Response response = jsonValdiator.jsonValdiator("", jsonData);
		if (response.getStatuscd().equals(AppConstants.ZERO)) {
			return response;
		}
		try {

			LoginRequest loginRequest = mapper.readValue(jsonData, LoginRequest.class);
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);

			String jwt = jwtUtils.generateJwtToken(authentication);
			header.put("token", jwt);
			response.setInfo("Token Generated successfully!");
			response.setStatus(AppConstants.ONE);
			response.setStatus("Token Generated successfully!");
			
			return response;
		} catch (Exception e) {
			response.setError(new Error(e.getMessage(), AppConstants.ZERO));
			e.printStackTrace();
		}
		return response;
	}

	@PostMapping("/signup")
	public Response registerUser(@RequestBody String jsonData) {

		JSONValdiator jsonValdiator = new JSONValdiator();
		Response response = jsonValdiator.jsonValdiator("", jsonData);
		if (response.getStatuscd().equals(AppConstants.ZERO)) {
			return response;
		}
		try {
			SignupRequest signUpRequest = mapper.readValue(jsonData, SignupRequest.class);

			User user = userService.findByEmail(signUpRequest.getEmail());

			if (NullUtil.isNotEmpty(user)) {
				response.setError(new Error("Error: Email is already in use!", AppConstants.ZERO));
			}else {
				user = new User();
			}

			//BeanUtils.copyProperties(signUpRequest, user);
			user = userMapper.convertSignupRequestToUser(signUpRequest);
			User usr = userService.createUser(user);
			
			CompletableFuture<User> future = CompletableFuture.supplyAsync(() -> usr);
			
			future.thenAcceptAsync(responseObj -> {
				// write send OTP to SMS & Email logic
			});
			response.setInfo("User registered successfully!");
			response.setStatus(AppConstants.ONE);
			response.setStatus("User registered successfully!");
			return response;
		} catch (Exception e) {
			response.setError(new Error(e.getMessage(), AppConstants.ZERO));
			e.printStackTrace();
		}
		return response;
	}
}
