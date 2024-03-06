package com.app.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.app.constants.AppConstants;
import com.app.response.Error;
import com.app.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
			AuthenticationException authException) throws IOException, ServletException {

		System.out.println("Unauthorized error: {}" + authException.getMessage());

		httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
		httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		Response response = new Response();
		response.setError(new Error("Unauthorized Request", AppConstants.ZERO));
		response.setMessage(authException.getMessage());
		response.setInfo(authException.getMessage());
		response.setStatuscd(String.valueOf(HttpServletResponse.SC_UNAUTHORIZED));
		response.setPath(httpRequest.getServletPath());
		
		Enumeration<String> headerNames = httpRequest.getHeaderNames();
		Map<String, String> headers = Maps.newHashMap();
		if (headerNames != null) {
			while (headerNames.hasMoreElements()) {
				
				String key = headerNames.nextElement();
				headers.put(key, httpRequest.getHeader(key));
			}
		}
		
		Map<String, String> appHeaders = removeHeaderValue(headers);
		response.setHeader(appHeaders);
		
		final ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(httpResponse.getOutputStream(), response);
	}
	
	public Map<String, String> removeHeaderValue(final Map<String, String> header, String... keys) {
		List<String> headerItems = new ArrayList<>();
		String[] array = { "host", "connection", "accept-encoding", "user-agent", "referer", "origin", "dnt",
				"accept-language", "accept", "content-length", "cookie", "x-forwarded-for", "x-forwarded-proto",
				"x-forwarded-port", "x-amzn-trace-id", "sec-ch-ua", "sec-ch-ua-mobile" };
		// Add all elements in array to ArrayList.
		Collections.addAll(headerItems, array);
		headerItems.forEach(headerItem -> {
			if (header.containsKey(headerItem)) {
				header.remove(headerItem);
			}
		});
		for (String key : keys) {
			if (header.containsKey(key))
				header.remove(key);
		}
		return header;
	}
}