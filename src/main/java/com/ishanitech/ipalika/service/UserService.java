package com.ishanitech.ipalika.service;

import java.util.List;
import java.util.Map;


import com.ishanitech.ipalika.dto.UserDTO;
import com.ishanitech.ipalika.dto.UserRegistrationDTO;
import com.ishanitech.ipalika.model.User;
/**
 * 
 * @author Umesh Bhujel
 *
 */
public interface UserService {
	User getUserByUsername(String username);
	User getUserById(int userId);
	public void addUser(UserRegistrationDTO user);
	public void deleteUser(int userId);
	public void changePassword(String newPassword, int userId);
	public void updateUserInfoByUserId(Map<String, Object> updates, int userId);
	public void disableUser(int userId);
	List<UserDTO> getAllUserInfo(int userId);
	Map<String, Boolean> checkDuplicateEntryParams(Map<String, String> userParameters);
	void updateUserInfoByAdmin(Map<String, Object> user, int userId);
	UserDTO getUserInfoByUserId(int userId);
	void changePasswordByAdmin(String newPassword, int userId);

}