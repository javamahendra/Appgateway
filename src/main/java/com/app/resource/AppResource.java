package com.app.resource;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.bean.AppDeleteRequest;
import com.app.bean.AppGetRequest;
import com.app.bean.AppPostRequest;
import com.app.bean.AppPutRequest;
import com.app.bean.AppRequest;
import com.app.handler.AppRequestProcessor;
import com.app.response.Response;

import static com.app.constants.AppConstants.GET;
import static com.app.constants.AppConstants.PUT;
import static com.app.constants.AppConstants.POST;
import static com.app.constants.AppConstants.DELETE;

@RestController
@RequestMapping("/api")
public class AppResource {

	// http://localhost:9091/api/v1.0/all?email=mahi
	
	@Autowired
	private AppRequestProcessor appRequestProcessor;

	@CrossOrigin(origins = "*")
	@GetMapping(value = "/{param1}/{param2}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response handleGetRequest(@RequestHeader Map<String, String> header, @PathVariable("param1") String param1,
			@PathVariable("param2") String param2, HttpServletRequest request) {

		AppRequest appRequest = new AppGetRequest(new String[] { GET, param1, param2 }, header, request.getParameterMap());
		Response response = appRequestProcessor.processRequest(appRequest);

		return response;
	}

	@CrossOrigin(origins = "*")
	@GetMapping(value = "/{param1}/{param2}/{param3}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response handleGetRequest(@RequestHeader Map<String, String> header, @PathVariable("param1") String param1,
			@PathVariable("param2") String param2, @PathVariable("param3") String param3, HttpServletRequest request) {

		AppRequest appRequest = new AppGetRequest(new String[] { GET, param1, param2, param3 }, header, request.getParameterMap());
		Response response = appRequestProcessor.processRequest(appRequest);

		return response;
	}

	@CrossOrigin(origins = "*")
	@GetMapping(value = "/{param1}/{param2}/{param3}/{param4}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response handleGetRequest(@RequestHeader Map<String, String> header, @PathVariable("param1") String param1,
			@PathVariable("param2") String param2, @PathVariable("param3") String param3,
			@PathVariable("param4") String param4, HttpServletRequest request) {

		AppRequest appRequest = new AppGetRequest(new String[] { GET, param1, param2, param3, param4 }, header, request.getParameterMap());
		Response response = appRequestProcessor.processRequest(appRequest);

		return response;
	}

	@CrossOrigin(origins = "*")
	@PostMapping(value = "{param1}/{param2}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response handlePostRequest(@RequestHeader Map<String, String> header, @PathVariable("param1") String param1,
			@PathVariable("param2") String param2, HttpServletRequest request, @RequestBody String jsonRequestPayload) {

		//String user = request.getRemoteUser();
		//String host = request.getRemoteHost();
		//String addr = request.getRemoteAddr();

		AppRequest appRequest = new AppPostRequest(new String[] { POST, param1, param2 }, header, request.getParameterMap(), jsonRequestPayload);
		Response response = appRequestProcessor.processRequest(appRequest);
		return response;
	}

	@CrossOrigin(origins = "*")
	@PostMapping(value = "{param1}/{param2}/{param3}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response handlePostRequest(@RequestHeader Map<String, String> header, @PathVariable("param1") String param1,
			@PathVariable("param2") String param2, @PathVariable("param3") String param3, HttpServletRequest request,
			@RequestBody String jsonRequestPayload) {
		
		AppRequest appRequest = new AppPostRequest(new String[] { POST, param1, param2, param3 }, header, request.getParameterMap(), jsonRequestPayload);
		Response response = appRequestProcessor.processRequest(appRequest);

		return response;
	}

	@CrossOrigin(origins = "*")
	@PostMapping(value = "{param1}/{param2}/{param3}/{param4}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response handlePostRequest(@RequestHeader Map<String, String> header, @PathVariable("param1") String param1,
			@PathVariable("param2") String param2, @PathVariable("param3") String param3,
			@PathVariable("param4") String param4, HttpServletRequest request, @RequestBody String jsonRequestPayload) {

		AppRequest appRequest = new AppPostRequest(new String[] { POST, param1, param2, param3, param4 }, header, request.getParameterMap(), jsonRequestPayload);
		Response response = appRequestProcessor.processRequest(appRequest);

		return response;
	}

	@CrossOrigin(origins = "*")
	@PutMapping(value = "{param1}/{param2}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response handlePutRequest(@RequestHeader Map<String, String> header, @PathVariable("param1") String param1,
			@PathVariable("param2") String param2, HttpServletRequest request, @RequestBody String jsonRequestPayload) {

		AppRequest appRequest = new AppPutRequest(new String[] { PUT, param1, param2 }, header, request.getParameterMap(), jsonRequestPayload);
		Response response = appRequestProcessor.processRequest(appRequest);
		return response;
	}

	@CrossOrigin(origins = "*")
	@PutMapping(value = "{param1}/{param2}/{param3}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response handlePutRequest(@RequestHeader Map<String, String> header, @PathVariable("param1") String param1,
			@PathVariable("param2") String param2, @PathVariable("param3") String param3, HttpServletRequest request,
			@RequestBody String jsonRequestPayload) {

		AppRequest appRequest = new AppPutRequest(new String[] { PUT, param1, param2, param3 }, header, request.getParameterMap(), jsonRequestPayload);
		Response response = appRequestProcessor.processRequest(appRequest);

		return response;
	}

	@CrossOrigin(origins = "*")
	@PutMapping(value = "{param1}/{param2}/{param3}/{param4}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response handlePutRequest(@RequestHeader Map<String, String> header, @PathVariable("param1") String param1,
			@PathVariable("param2") String param2, @PathVariable("param3") String param3,
			@PathVariable("param4") String param4, HttpServletRequest request, @RequestBody String jsonRequestPayload) {

		AppRequest appRequest = new AppPutRequest(new String[] { PUT, param1, param2, param3, param4 }, header, request.getParameterMap(), jsonRequestPayload);
		Response response = appRequestProcessor.processRequest(appRequest);

		return response;
	}

	@CrossOrigin(origins = "*")
	@DeleteMapping(value = "{param1}/{param2}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response handleDeleteRequest(@RequestHeader Map<String, String> header,
			@PathVariable("param1") String param1, @PathVariable("param2") String param2, HttpServletRequest request) {

		AppRequest appRequest = new AppDeleteRequest(new String[] { DELETE, param1, param2 }, header, request.getParameterMap());
		Response response = appRequestProcessor.processRequest(appRequest);
		return response;
	}

	@CrossOrigin(origins = "*")
	@DeleteMapping(value = "{param1}/{param2}/{param3}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response handleDeleteRequest(@RequestHeader Map<String, String> header,
			@PathVariable("param1") String param1, @PathVariable("param2") String param2,
			@PathVariable("param3") String param3, HttpServletRequest request) {

		AppRequest appRequest = new AppDeleteRequest(new String[] { DELETE, param1, param2, param3 }, header, request.getParameterMap());
		Response response = appRequestProcessor.processRequest(appRequest);

		return response;
	}

	@CrossOrigin(origins = "*")
	@DeleteMapping(value = "{param1}/{param2}/{param3}/{param4}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response handleDeleteRequest(@RequestHeader Map<String, String> header,
			@PathVariable("param1") String param1, @PathVariable("param2") String param2,
			@PathVariable("param3") String param3, @PathVariable("param4") String param4, HttpServletRequest request) {

		AppRequest appRequest = new AppDeleteRequest(new String[] { DELETE, param1, param2, param3, param4 }, header, request.getParameterMap());
		Response response = appRequestProcessor.processRequest(appRequest);

		return response;
	}

}
