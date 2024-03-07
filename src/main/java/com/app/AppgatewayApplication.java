package com.app;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.app.model.User;
import com.app.service.UserService;

@EnableJpaAuditing
@SpringBootApplication
//@EnableConfigurationProperties(value = { ConfigProperties.class })
public class AppgatewayApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(AppgatewayApplication.class);
	}

	public static void main(String[] args) {
		// base URL - http://localhost:9091/
		//http://localhost:9091/h2-console/
		SpringApplication.run(AppgatewayApplication.class, args);
	}

	@Autowired
	private UserService userService;
	//@Autowired
	//private ConfigProperties configProperties;
	
	

	@PostConstruct
	public void init() {

		//System.out.println(configProperties);
		
		User user1 = new User();
		user1.setEmail("javabymahendra@gmail.com");
		user1.setMobileno("9876543210");
		user1.setPassword("123456");
		user1.setFirstname("Mahendra");
		user1.setLastname("Gadiparthi");

		userService.createUser(user1);
		userService.getAllUser().forEach(usr -> {
			System.out.println("Id :" + usr.getId() + ", " + usr);
		});

	}

}
