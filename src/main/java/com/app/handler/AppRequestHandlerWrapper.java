package com.app.handler;

import java.util.List;

public class AppRequestHandlerWrapper extends AppRequestHandler{

	private String[] urlParams;
	private List<Param> requestParams;
	private List<Param> requestHeaders;
	private String payloadTemplate;
	
	public AppRequestHandlerWrapper(AppRequestHandlerHelper handlerHelper){
		super(handlerHelper);
	}
	
	public String[] getUrlParams() {
		return urlParams;
	}

	public void setUrlParams(String[] urlParams) {
		this.urlParams = urlParams;
	}

	public List<Param> getRequestParams() {
		return requestParams;
	}
	public void setRequestParams(List<Param> requestParams) {
		this.requestParams = requestParams;
	}
	public List<Param> getRequestHeaders() {
		return requestHeaders;
	}
	public void setRequestHeaders(List<Param> requestHeaders) {
		this.requestHeaders = requestHeaders;
	}
	
	public String getPayloadTemplate() {
		return payloadTemplate;
	}
	
	public void setPayloadTemplate(String payloadTemplate) {
		this.payloadTemplate = payloadTemplate;
	}
}
