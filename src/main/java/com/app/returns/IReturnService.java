package com.app.returns;

import java.util.Map;

import com.app.response.Response;

public interface IReturnService {

	public Response getResponse(Map<String, String> header, Map<String, Object> queryStringMap, String requestUrl, Response response);

	public Response postResponse(Map<String, String> header, Map<String, Object> queryStringMap, String requestUrl);

	public Response putResponse(Map<String, String> header, Map<String, Object> queryStringMap, String requestUrl);

	public Response deleteResponse(Map<String, String> header, Map<String, Object> queryStringMap, String requestUrl);

}
