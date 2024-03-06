package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.model.User;
import com.app.service.UserService;

@Controller
public class ApiViewController {

	@Autowired
	private UserService userService;
	
	// http://localhost:9091/swagger-ui
	@GetMapping("/swagger-ui")
	public String swaggerUi(ModelMap model) {
		
		User user = userService.findByEmail("mahendra.gadiparthi@tcs.com");
		System.out.println(user.getKeys());
		
		model.addAttribute("headerkeys", user.getKeys());
		
		return "swagger";
	}
}
