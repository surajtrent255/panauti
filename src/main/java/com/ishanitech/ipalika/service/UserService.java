package com.ishanitech.ipalika.service;

import com.ishanitech.ipalika.dto.UserDTO;
import com.ishanitech.ipalika.model.User;
/**
 * 
 * @author Umesh Bhujel
 *
 */
public interface UserService {
	User getUserByUsername(String username);
	public void addUser(UserDTO user);
}
