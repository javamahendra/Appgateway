package com.app.handler;

import static com.app.constants.AppConstants.DELETE;
import static com.app.constants.AppConstants.EMAIL;
import static com.app.constants.AppConstants.GET;
import static com.app.constants.AppConstants.POST;
import static com.app.constants.AppConstants.PUT;
import static com.app.constants.AppConstants.USER_CLIENTID;
import static com.app.constants.AppConstants.USER_CLIENTSECRET;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

@Service
public class AppRequestHandlerRegister {

	private Map<String, AppRequestHandler> handlerMap = Maps.newHashMap();

	@Autowired
	private AppAuthenticationHandlerHelper appAuthenticationHandlerHelper;
	
	@Autowired
	private AppGetRequestHandlerHelper appGetRequestHandlerHelper;

	@Autowired
	private AppPostRequestHandlerHelper appPostRequestHandlerHelper;
	
	@Autowired
	private AppPutRequestHandlerHelper appPutRequestHandlerHelper;	
	
	@Autowired
	private AppDeleteRequestHandlerHelper appDeleteRequestHandlerHelper;
	
	public AppRequestHandler getRegisterRequests(String apiCode) {
		return handlerMap.get(apiCode);
	}

	@PostConstruct
	public void init() {
		System.out.println("-------------------------init() Method Start------------------------");
		//authentication api's
		handlerMap.put("post_v1.0_gentoken", authenticationRequestRegister(new String[] { POST, "api", "v1.0", "gentoken" }, "generatetoken.json"));
		
		//get api's
		handlerMap.put("get_v1.0_authenticate", getRequestRegister(new String[] { GET, "api", "v1.0", "authenticate" }));
		handlerMap.put("get_v1.0_employees", getRequestRegister(new String[] { GET, "api", "v1.0", "employees" }));
		handlerMap.put("get_v1.0_employee", getRequestRegister(new String[] { GET, "api", "v1.0", "employee" }));
		
		//post api's
		handlerMap.put("post_v1.0_create_employee", postRequestRegister(new String[] { POST, "api", "v1.0", "create", "employee" }, "employee.json"));
		
		//put api's
		handlerMap.put("put_v1.0_update_employee", putRequestRegister(new String[] { PUT, "api", "v1.0", "update", "employee" }, "employee.json"));
		
		//delete api's
		handlerMap.put("detele_v1.0_delete_employee", deleteRequestRegister(new String[] { DELETE, "api", "v1.01", "delete", "employee" }));
		System.out.println("-------------------------init() Method End------------------------");
	}

	private AppRequestHandlerWrapper authenticationRequestRegister(String[] urlParams, String template) {

		AppRequestHandlerWrapper appRequestHandlerWrapper = new AppRequestHandlerWrapper(appAuthenticationHandlerHelper);
		
		List<Param> headerParams = new ArrayList<>();
		headerParams.add(new Param(USER_CLIENTID, true));
		headerParams.add(new Param(USER_CLIENTSECRET, true));

		List<Param> reqParams = new ArrayList<>();
		reqParams.add(new Param(EMAIL, true));

		appRequestHandlerWrapper.setRequestParams(reqParams);
		appRequestHandlerWrapper.setRequestHeaders(headerParams);
		appRequestHandlerWrapper.setUrlParams(urlParams);
		appRequestHandlerWrapper.setPayloadTemplate(template);
		return appRequestHandlerWrapper;
	}
	
	private AppRequestHandlerWrapper getRequestRegister(String[] urlParams) {
		AppRequestHandlerWrapper appRequestHandlerWrapper = new AppRequestHandlerWrapper(appGetRequestHandlerHelper);
		
		List<Param> headerParams = new ArrayList<>();
		headerParams.add(new Param(USER_CLIENTID, true));
		headerParams.add(new Param(USER_CLIENTSECRET, true));

		List<Param> reqParams = new ArrayList<>();
		reqParams.add(new Param(EMAIL, true));

		appRequestHandlerWrapper.setRequestParams(reqParams);
		appRequestHandlerWrapper.setRequestHeaders(headerParams);
		appRequestHandlerWrapper.setUrlParams(urlParams);

		return appRequestHandlerWrapper;
	}

	private AppRequestHandlerWrapper postRequestRegister(String[] urlParams, String template) {
		AppRequestHandlerWrapper appRequestHandlerWrapper = new AppRequestHandlerWrapper(appPostRequestHandlerHelper);
		
		List<Param> headerParams = new ArrayList<>();
		headerParams.add(new Param(USER_CLIENTID, true));
		headerParams.add(new Param(USER_CLIENTSECRET, true));

		List<Param> reqParams = new ArrayList<>();
		reqParams.add(new Param(EMAIL, true));

		appRequestHandlerWrapper.setRequestParams(reqParams);
		appRequestHandlerWrapper.setRequestHeaders(headerParams);
		appRequestHandlerWrapper.setPayloadTemplate(template);
		appRequestHandlerWrapper.setUrlParams(urlParams);

		return appRequestHandlerWrapper;
	}

	private AppRequestHandlerWrapper putRequestRegister(String[] urlParams, String template) {
		AppRequestHandlerWrapper appRequestHandlerWrapper = new AppRequestHandlerWrapper(appPutRequestHandlerHelper);
		
		List<Param> headerParams = new ArrayList<>();
		headerParams.add(new Param(USER_CLIENTID, true));
		headerParams.add(new Param(USER_CLIENTSECRET, true));

		List<Param> reqParams = new ArrayList<>();
		reqParams.add(new Param(EMAIL, true));

		appRequestHandlerWrapper.setRequestParams(reqParams);
		appRequestHandlerWrapper.setRequestHeaders(headerParams);
		appRequestHandlerWrapper.setPayloadTemplate(template);
		appRequestHandlerWrapper.setUrlParams(urlParams);

		return appRequestHandlerWrapper;
	}

	private AppRequestHandlerWrapper deleteRequestRegister(String[] urlParams) {
		AppRequestHandlerWrapper appRequestHandlerWrapper = new AppRequestHandlerWrapper(appDeleteRequestHandlerHelper);
		
		List<Param> headerParams = new ArrayList<>();
		headerParams.add(new Param(USER_CLIENTID, true));
		headerParams.add(new Param(USER_CLIENTSECRET, true));

		List<Param> reqParams = new ArrayList<>();
		reqParams.add(new Param(EMAIL, true));

		appRequestHandlerWrapper.setRequestParams(reqParams);
		appRequestHandlerWrapper.setRequestHeaders(headerParams);
		appRequestHandlerWrapper.setUrlParams(urlParams);

		return appRequestHandlerWrapper;
	}
}
