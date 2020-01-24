package com.ishanitech.ipalika.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ishanitech.ipalika.dto.UserDTO;
import com.ishanitech.ipalika.exception.CustomSqlException;
import com.ishanitech.ipalika.service.UserService;

@RequestMapping("/admin")
@RestController
public class AdminController {
	private final UserService userService;
	
	public AdminController(UserService userService) {
		this.userService = userService;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/user")
	public void createUser(@RequestBody UserDTO user) throws CustomSqlException {
		userService.addUser(user);
	}
}
