package com.app.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.app.response.Error;
import com.app.model.User;
import com.app.response.Response;
import com.app.service.UserService;
import com.app.util.MapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/user")
public class UserRestController {

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private UserService userService;
	@Autowired
	private MapperUtil mapperUtil;

	@GetMapping("/")
	public String hello() throws JsonProcessingException {

		Response response = new Response();

		Map<String, String> headerMap = new HashMap<>();

		headerMap.put("Authorization", "Bearer :Token");
		headerMap.put("clientId", "client-id");
		headerMap.put("clientsecret", "client-secret");

		response.setData("user info");
		response.setStatuscd("1001");
		response.setStatusdesc("Success");
		//response.setHeader(headerMap);

		return mapper.writeValueAsString(response);
	}

	@PostMapping("/create")
	public ResponseEntity<Response> createUser(@RequestBody User user) {

		User usr = userService.createUser(user);
		
	
		Response response = new Response();
		response.setStatuscd(HttpStatus.OK.toString());
		response.setStatusdesc("User Id :"+usr.getId());
		response.setData(mapperUtil.convertObjectToString(usr));
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<User> updateUser(@RequestParam(name = "id", required = true) String id, @RequestBody User user) throws JsonProcessingException {

		User usr = userService.updateUser(id, user);
		Response response = new Response();
		response.setStatuscd(HttpStatus.ACCEPTED.toString());
		response.setStatusdesc("User data updated :"+usr.getId());
		response.setData(mapperUtil.convertObjectToString(usr));
		return new ResponseEntity<User>(usr, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteUser(@RequestParam(name = "id", required = true) String id) throws JsonProcessingException {

		String info = userService.deleteUser(id) ? "user details deleted in db" :"deatils not found in db";
		Response response = new Response();
		response.setStatuscd(HttpStatus.OK.toString());
		response.setStatusdesc(info);
		//response.setData(mapperUtil.convertObjectToString(usr));
		return new ResponseEntity<String>(info, HttpStatus.FOUND);
	}

	@GetMapping("/get")
	public ResponseEntity<Response> getUser(@RequestParam(name = "id", required = true) String id) throws JsonProcessingException {

		User usr = userService.getUser(id);
		Response response = new Response();
		if(usr == null) {
			
			response.setData(mapperUtil.convertObjectToString(usr));
			response.setStatuscd(HttpStatus.FOUND.toString());
			response.setStatusdesc("User data for id :" + id);
			return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
		}
		response.setStatuscd(HttpStatus.NOT_FOUND.toString());
		response.setStatusdesc("User detials not found for id :" + id);
		response.setError(new Error("User detials not found for id :" + id, "101"));
		return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
	}

	@GetMapping("/all")
	public ResponseEntity<Response> getAllUser() throws JsonProcessingException {

		List<User> usrs = userService.getAllUser();
		Response response = new Response();
		response.setData(mapperUtil.convertObjectToString(usrs));
		response.setStatuscd(HttpStatus.FOUND.toString());
		response.setStatusdesc("User data fetched successfully");
		
		System.out.println(" Json response :"+response);
		return new ResponseEntity<Response>(response, HttpStatus.ACCEPTED);
	}

}
