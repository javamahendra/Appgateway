package com.app.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.app.bean.AppPostRequest;
import com.app.bean.AppRequest;
import com.app.constants.AppConstants;
import com.app.model.User;
import com.app.request.LoginRequest;
import com.app.response.Error;
import com.app.response.Response;
import com.app.service.UserService;
import com.app.util.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AppAuthenticationHandlerHelper extends AppRequestHandlerHelper {

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	private JwtUtils jwtUtils;

	@Override
	protected void execute(AppRequest appRequest, AppRequestHandler requestHandler, User user, Response response) {
		try {

			AppPostRequest appPostRequest = (AppPostRequest) appRequest;

			Map<String, String> headers = headerHelper.removeHeaderValue(appRequest.getHeaders());

			response.setHeader(headers);

			LoginRequest loginRequest = mapper.readValue(appPostRequest.getJsonRequestPayload(), LoginRequest.class);
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);

			String jwt = jwtUtils.generateJwtToken(authentication);
			response.setData("{\"token\":\"" + jwt + "\"}");
			response.setInfo("Token Generated successfully!");
			response.setStatus(AppConstants.ONE);
			response.setStatus("Token Generated successfully!");

		} catch (

		Exception e) {
			response.setError(new Error(e.getMessage(), AppConstants.ZERO));
			e.printStackTrace();
		}

	}
}
