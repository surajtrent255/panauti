package com.ishanitech.ipalika.converter.impl;

import java.util.stream.Collectors;

import com.ishanitech.ipalika.converter.BaseConverter;
import com.ishanitech.ipalika.dto.UserDTO;
import com.ishanitech.ipalika.model.User;

/**
 *Converter class to convert from and to User and UserDto
 *@author <b> Umesh Bhujel
 *@since 1.0
 */
public class UserConverter extends BaseConverter<User, UserDTO> {

	
	/**
	 * Converts {@code UserDTO} object to {@code User} entity object.
	 * @param dto UserDTO object
	 * @return {@code User} entity object
	 */
	@Override
	public User fromDto(UserDTO dto) {
		return null;
	}

	/**
	 * Converts {@code User} entity object to {@code UserDTO} object.
	 * @param entity User entity object
	 * @return {@code UserDTO} object.
	 */
	@Override
	public UserDTO fromEntity(User entity) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(entity.getUsername());
		userDTO.setEmail(entity.getEmail());
		userDTO.setFullName(String.format("%s %s %s", entity.getFirstName(), entity.getMiddleName(), entity.getLastName()));
		userDTO.setMobileNumber(entity.getMobileNumber());
		userDTO.setLocked(entity.isLocked());
		userDTO.setEnabled(entity.isEnabled());
		userDTO.setExpired(entity.isExpired());
		userDTO.setFirstLogin(entity.isFirstLogin());
		userDTO.setRoles(entity.getRole().stream().map(role -> role.getRole()).collect(Collectors.toList()));
		return userDTO;
	}

}
