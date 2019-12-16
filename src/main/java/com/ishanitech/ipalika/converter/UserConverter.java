/**
 * @author Umesh Bhujel <yoomesbhujel@gmail.com>
 * Since Dec 13, 2019
 */
package com.ishanitech.ipalika.converter;

import java.util.Date;
import java.util.function.Function;

import com.ishanitech.ipalika.dto.UserDTO;
import com.ishanitech.ipalika.model.User;

public class UserConverter extends Converter<UserDTO, User> {

	/**
	 * @param fromDto. Function to converted from dto to entity object.
	 * @param fromEntity. Function to convert from entity to dto object.
	 */
	public UserConverter(Function<UserDTO, User> fromDto, Function<User, UserDTO> fromEntity) {
		super(userDto -> {
				User user = new User();
				user.setFirstLogin(true);
				user.setUsername(userDto.getUsername());
				user.setEmail(userDto.getEmail());
				user.setFirstName(userDto.getFirstName());
				user.setMiddleName(userDto.getMiddleName());
				user.setLastName(userDto.getLastName());
				user.setPassword(userDto.getPassword());
				user.setMobileNumber(userDto.getMobileNumber());
				user.setEnabled(true);
				user.setExpired(false);
				user.setRegisteredDate(new Date());
				user.setLocked(false);
				return user;
			}, user -> {
				UserDTO userDTO = new UserDTO();
				userDTO.setUsername(user.getUsername());
				userDTO.setEmail(user.getEmail());
				userDTO.setFirstName(user.getFirstName());
				userDTO.setMiddleName(user.getMiddleName());
				userDTO.setLastName(user.getLastName());
				return userDTO;
			}
		);
	}
}

