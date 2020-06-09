package com.restfull.app.controller;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;

import com.restfull.app.bean.User;
import com.restfull.app.dao.UserDaoService;
import com.restfull.app.exception.UserNotFoundException;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;

	@GetMapping(path="/users")
	public List<User> findAll() {
		return service.findAll();
	}

	@PostMapping(path="/users")
	public ResponseEntity<Object> save(@Valid @RequestBody User user) {
		User savedUser =  service.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		// 201 is response status for creation as per http standered
		return ResponseEntity.created(location).build();
	}

	@GetMapping(path="/users/{id}")
	public User findOne(@PathVariable int id) {
		User user = service.findOne(id);
		
		if (user==null)
			throw new UserNotFoundException("id-"+id);
		
		//Resource resource = new Resource(user);
		Link link = ControllerLinkBuilder
	            .linkTo(ControllerLinkBuilder
	            .methodOn(UserResource.class).findAll())
	            .withRel("all-users");
		user.add(link);
		return user;
	}
	
	@DeleteMapping(path="/users/{id}")
	public User deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		
		if (user==null)
			throw new UserNotFoundException("id-"+id);
		return user;
	}
}
