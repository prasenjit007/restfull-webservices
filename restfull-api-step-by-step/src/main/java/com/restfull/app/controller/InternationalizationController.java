package com.restfull.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InternationalizationController {
	
	@GetMapping(path="/greetings")
	public String helloWorld() {
		return "Good Morning";
	}

}
