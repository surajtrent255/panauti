package com.ishanitech.ipalika.service;

import java.util.Map;

import com.ishanitech.ipalika.dto.UserDTO;
import com.ishanitech.ipalika.model.User;
/**
 * 
 * @author Umesh Bhujel
 *
 */
public interface UserService {
	User getUserByUsername(String username);
	User getUserById(int userId);
	public void addUser(UserDTO user);
	public void deleteUser(int userId);
	public void changePassword(String newPassword, int userId);
	public void updateUserInfoByUserId(Map<String, Object> updates, int userId);
	public void disableUser(int userId);
}
