package com.app.bean;

import java.util.Map;

import org.springframework.http.HttpMethod;

public class AppPostRequest extends AppRequest {

	private String jsonRequestPayload;
	
	public AppPostRequest(String[] urlParams, Map<String, String> headers, Map<String, String[]> reqParams, String jsonRequestPayload){
		super(urlParams, headers, reqParams);
		this.jsonRequestPayload = jsonRequestPayload;
	}

	@Override
	public HttpMethod getMethod() {
		return HttpMethod.POST;
	}

	public String getJsonRequestPayload() {
		return jsonRequestPayload;
	}
	
}
