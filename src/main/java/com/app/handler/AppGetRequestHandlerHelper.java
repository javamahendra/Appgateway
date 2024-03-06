package com.app.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.app.bean.AppGetRequest;
import com.app.bean.AppRequest;
import com.app.constants.AppConstants;
import com.app.model.User;
import com.app.response.Response;
import com.app.returns.IReturnService;

@Service
public class AppGetRequestHandlerHelper extends AppRequestHandlerHelper {

	@Autowired
	private IReturnService iReturnService;

	@Value("${GET_ALL_EMPLOYEES}")
	private String getEmployeesApi;
	@Value("${GET_EMPLOYEE}")
	private String getEmployeeApi;

	@Override
	protected void execute(AppRequest appRequest, AppRequestHandler requestHandler, User user, Response response) {

		AppGetRequest appGetRequest = (AppGetRequest) appRequest;

		if (appGetRequest.getUrlParams()[appRequest.getUrlParams().length - 1].equals("authenticate")) {
			response.setInfo("Token Aunthentication success...!");
			response.setStatus(AppConstants.ONE);
			response.setStatus("Token Aunthentication success..!");
		} else {

			String requestUrl = resolveUrl(headerHelper.getUrl(appGetRequest.getHeaders(), user),
					appGetRequest.getUrlParams());
			response = iReturnService.getResponse(appRequest.getHeaders(), null, requestUrl, response);
		}
		Map<String, String> headers = headerHelper.removeHeaderValue(appRequest.getHeaders());
		response.setHeader(headers);

	}

	private String resolveUrl(String baseUrl, String[] urlParams) {

		StringBuilder builder = new StringBuilder();

		for (String param : urlParams) {
			if (param.equals(AppConstants.GET)) {
				continue;
			}
			builder.append("/" + param);
		}

		return String.format("%s/%s", baseUrl, builder.toString());
	}

}
