package com.app.bean;

import java.util.Map;

import org.springframework.http.HttpMethod;

public abstract class AppRequest {

	private String[] urlParams;
	private Map<String, String> headers;
	private Map<String, String[]> reqParams;

	public abstract HttpMethod getMethod();
	
	public AppRequest() {
		super();
	}

	public AppRequest(String[] urlParams, Map<String, String> headers, Map<String, String[]> reqParams) {
		this.urlParams = urlParams;
		this.headers = headers;
		this.reqParams = reqParams;
	}


	public String[] getUrlParams() {
		return urlParams;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public Map<String, String[]> getReqParams() {
		return reqParams;
	}

}
