package com.app.returns;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.app.constants.AppConstants;
import com.app.response.Error;
import com.app.response.Response;
import com.app.util.NullUtil;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class ReturnServiceImpl implements IReturnService {

	@Override
	public Response getResponse(Map<String, String> header, Map<String, Object> queryStringMap, String requestUrl, Response response) {
		System.out.println("header ::" + header.toString());
		System.out.println("queryStringMap ::" + queryStringMap);
		System.out.println("requestUrl ::" + requestUrl);

		response.setHeader(header);
		response.setStatuscd(AppConstants.ONE);
		response.setStatusdesc(AppConstants.SUCCESS);
		try {
			HttpResponse<JsonNode> httpResponse = null;
			if (NullUtil.isNotEmpty(queryStringMap)) {
				httpResponse = Unirest.get(requestUrl).queryString(queryStringMap).asJson();
			} else {
				httpResponse = Unirest.get(requestUrl).asJson();
			}

			if (httpResponse.getStatus() == AppConstants.TWOHUNDRED) {
				if (NullUtil.isNotEmpty(httpResponse.getBody())) {
					JSONObject object = httpResponse.getBody().getObject();
					if (object.has(AppConstants.DATA)) {
						
						System.out.println(object.toString());
						//JSONArray jsonArray = object.getJSONArray(AppConstants.DATA);
						
						String data = object.getJSONObject(AppConstants.DATA).toString();
						
						response.setData(data);
					}
				}
			}

		} catch (UnirestException e) {
			response.setStatuscd(AppConstants.ZERO);
			response.setStatusdesc(AppConstants.FAILED);
			response.setError(new Error("invalid response came", AppConstants.ZERO));
		}

		return response;
	}

	@Override
	public Response postResponse(Map<String, String> header, Map<String, Object> queryStringMap, String requestUrl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response putResponse(Map<String, String> header, Map<String, Object> queryStringMap, String requestUrl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteResponse(Map<String, String> header, Map<String, Object> queryStringMap, String requestUrl) {
		// TODO Auto-generated method stub
		return null;
	}

}
