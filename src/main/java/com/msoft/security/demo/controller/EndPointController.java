package com.msoft.security.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndPointController {

	@GetMapping("/home")
	public String getHome() {
		return "This is a home page access by all user's without authenticate";
	}
	
	@GetMapping("/user/home")
	public String getUserHome() {
		return "This is a home page access by all user's who has USER role";
	}
	
	@GetMapping("/admin/home")
	public String getAdminHome() {
		return "This is a home page access by all user's who has ADMIN role";
	}
	
}
