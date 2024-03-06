package com.app.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.response.Error;
import com.app.bean.AppRequest;
import com.app.response.Response;
import com.app.util.NullUtil;

import static com.app.constants.AppConstants.ZERO;

@Service
public class AppRequestProcessor {
	
	@Autowired
	private AppRequestHandlerFactory appRequestHandlerFactory;
	
	public Response processRequest(AppRequest appRequest) {
		
		AppRequestHandler appRequestHandler = appRequestHandlerFactory.acceptUrlRequest(appRequest.getUrlParams());
		if(NullUtil.isNotEmpty(appRequestHandler)) {
			Response response = appRequestHandler.handleRequest(appRequest);
			return response;
		} else {
			System.out.println("AppRequestProcessor : processRequest : Invalid request for input %s "+ String.join("_", appRequest.getUrlParams()));
			return new Response(ZERO, new Error("Invalid request!", ZERO));
		}
	}
	
	

}
