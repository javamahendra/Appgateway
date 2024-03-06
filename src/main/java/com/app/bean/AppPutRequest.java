package com.app.bean;

import java.util.Map;

import org.springframework.http.HttpMethod;

public class AppPutRequest extends AppRequest {

	private String jsonRequestPayload;

	public AppPutRequest(String[] urlParams, Map<String, String> headers, Map<String, String[]> reqParams, String jsonRequestPayload) {
		super(urlParams, headers, reqParams);
		this.jsonRequestPayload = jsonRequestPayload;
	}

	@Override
	public HttpMethod getMethod() {
		return HttpMethod.PUT;
	}

	public String getJsonRequestPayload() {
		return jsonRequestPayload;
	}

}
