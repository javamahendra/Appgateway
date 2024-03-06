package com.app.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.bean.AppRequest;
import com.app.response.Response;

@Component
public abstract class AppRequestHandler {

	@Autowired
	private AppRequestHandlerHelper handlerHelper;

	public AppRequestHandler(AppRequestHandlerHelper handlerHelper) {
		this.handlerHelper = handlerHelper;
	}

	public Response handleRequest(AppRequest appRequest) {
		return handlerHelper.handle(appRequest, this);
	}

	public abstract List<Param> getRequestParams();

	public abstract List<Param> getRequestHeaders();

	public abstract String getPayloadTemplate();

	public abstract String[] getUrlParams();

}
