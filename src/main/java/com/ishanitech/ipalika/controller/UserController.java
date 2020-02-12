package com.ishanitech.ipalika.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ishanitech.ipalika.dto.UserDTO;
import com.ishanitech.ipalika.exception.CustomSqlException;
import com.ishanitech.ipalika.security.CustomUserDetails;
import com.ishanitech.ipalika.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {
	
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@Secured({"ROLE_SUPER_ADMIN", "ROLE_CENTRAL_ADMIN"})
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void createUser(@RequestBody UserDTO user) throws CustomSqlException {
		userService.addUser(user);
	}
	
	//@PreAuthorize("hasRole('ROLE_SUPER_ADMIN') OR hasRole('ROLE_CENTRAL_ADMIN')")
	@Secured({"ROLE_SUPER_ADMIN", "ROLE_CENTRAL_ADMIN"})
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") int userId, @AuthenticationPrincipal CustomUserDetails user) {
		userService.deleteUser(userId);
	}
	
	@Secured({"ROLE_SUPER_ADMIN", "ROLE_CENTRAL_ADMIN"})
	@PostMapping("/{userId}")
	public void disableUser(@PathVariable("userId") int userId) {
		userService.disableUser(userId);
	}
	
	
	@PatchMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateUserInfo(@PathVariable("userId") int userId,
			@RequestBody Map<String, Object> updates) {
		userService.updateUserInfoByUserId(updates, userId);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{userId}/passoword")
	public void changePassword(@RequestBody String password, @PathVariable("userId") int userId) {
		userService.changePassword(password, userId);
	}
	
}
