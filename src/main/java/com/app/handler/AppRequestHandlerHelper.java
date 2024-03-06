package com.app.handler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.bean.AppDeleteRequest;
import com.app.bean.AppGetRequest;
import com.app.bean.AppPostRequest;
import com.app.bean.AppPutRequest;
import com.app.bean.AppRequest;
import com.app.constants.AppConstants;
import com.app.helper.AppMessages;
import com.app.helper.HeaderHelper;
import com.app.model.User;
import com.app.response.Response;
import com.app.service.UserService;
import com.app.util.NullUtil;
import com.app.validator.JSONValdiator;

@Component
public abstract class AppRequestHandlerHelper {

	private static String NULL_CODE_SUFIX = "_null";

	@Autowired
	protected UserService userService;

	@Autowired
	protected HeaderHelper headerHelper;

	public Response handle(AppRequest appRequest, AppRequestHandler requestHandler) {

		preHandle(appRequest, requestHandler);
		Response response = validate(appRequest, requestHandler);
		if (isResponseNotValid(response)) {
			return response;
		}
		Map<String, String> headers = appRequest.getHeaders();
		Map<String, String[]> reqParams = appRequest.getReqParams();
		String[] email = reqParams.get("email");
		User user = userService.findByEmail(email[0].toLowerCase());
		
		response = headerHelper.isEnableKeys(headers, user, response);
		if (isResponseNotValid(response)) {
			return response;
		}
		execute(appRequest, requestHandler, user, response);
		postHandle(response);
		return response;
	}

	protected abstract void execute(AppRequest appRequest, AppRequestHandler requestHandler, User user,	Response response);

	protected Response validate(AppRequest appRequest, AppRequestHandler requestHandler) {
		Response response = new Response();
		validateHeaders(appRequest, requestHandler, response);
		if (isResponseNotValid(response)) {
			return response;
		}
		validateReqParams(appRequest, requestHandler, response);
		if (isResponseNotValid(response)) {
			return response;
		}
		validatePayload(appRequest, requestHandler, response);
		return response;
	}

	protected void validateHeaders(AppRequest appRequest, AppRequestHandler requestHandler, Response response) {
		Map<String, String> headerMap = appRequest.getHeaders();
		List<Param> headers = requestHandler.getRequestHeaders();
		for (Param header : headers) {
			if (header.isMandatory()) {
				if (!headerMap.containsKey(header.getName())) {
					response.setStatuscd(AppConstants.ZERO);
					response.setStatusdesc(AppMessages.getMessage(header.getName()) + NULL_CODE_SUFIX);
					break;
				}
			}
		}
	}

	protected void validateReqParams(AppRequest appRequest, AppRequestHandler requestHandler, Response response) {
		Map<String, String[]> reqParams = appRequest.getReqParams();
		List<Param> params = requestHandler.getRequestParams();
		for (Param param : params) {
			if (param.isMandatory()) {
				if (!reqParams.containsKey(param.getName())) {
					response.setStatuscd(AppConstants.ZERO);
					response.setStatusdesc(AppMessages.getMessage(param.getName()) + NULL_CODE_SUFIX);
					break;
				}
			}
		}
	}

	protected void validatePayload(AppRequest appRequest, AppRequestHandler requestHandler, Response response) {

		if (!(appRequest instanceof AppGetRequest) && !(appRequest instanceof AppDeleteRequest)) {
			String payload = null;
			if (appRequest instanceof AppPostRequest) {
				payload = ((AppPostRequest) appRequest).getJsonRequestPayload();
			}else if(appRequest instanceof AppPutRequest) {
				payload = ((AppPutRequest) appRequest).getJsonRequestPayload();
			}
			if (NullUtil.isEmpty(payload)) {
				response.setStatuscd(AppConstants.ZERO);
				response.setStatusdesc(AppMessages.getMessage(AppConstants.REQ_PAYLOAD_NULL));
			} else {
				JSONValdiator jsonValdiator = new JSONValdiator();
				jsonValdiator.jsonValdiator(requestHandler.getPayloadTemplate(), payload, response);
			}
		}
	}

	protected void preHandle(AppRequest appRequest, AppRequestHandler requestHandler) {
		
	}

	protected void postHandle(Response response) {
		
	}

	protected boolean isResponseNotValid(Response response) {
		return NullUtil.isNotEmpty(response.getStatuscd()) && response.getStatuscd().equals(AppConstants.ZERO);
	}
}
