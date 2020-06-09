package com.restfull.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@GetMapping(path="/")
	public String root() {
		return "UP";
	}
	
	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "<h1> Hello World </h1>";
	}

}
