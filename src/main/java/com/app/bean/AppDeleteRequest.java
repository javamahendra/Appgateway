package com.app.bean;

import java.util.Map;

import org.springframework.http.HttpMethod;

public class AppDeleteRequest extends AppRequest {

	public AppDeleteRequest(String[] urlParams, Map<String, String> headers, Map<String, String[]> reqParams) {
		super(urlParams, headers, reqParams);
	}

	@Override
	public HttpMethod getMethod() {
		return HttpMethod.DELETE;
	}

}
