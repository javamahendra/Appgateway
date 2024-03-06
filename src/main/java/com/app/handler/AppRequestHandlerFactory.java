package com.app.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AppRequestHandlerFactory {

	@Autowired
	private AppRequestHandlerRegister appRequestHandlerRegister;
	
	public AppRequestHandler acceptUrlRequest(String[] urlParams){
		String apiCode = String.join("_", urlParams);
		return appRequestHandlerRegister.getRegisterRequests(apiCode);
	}
}
