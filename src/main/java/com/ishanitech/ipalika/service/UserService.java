package com.ishanitech.ipalika.service;

import java.util.List;

import com.ishanitech.ipalika.model.User;
/**
 * 
 * @author Umesh Bhujel
 *
 */
public interface UserService {
	User getUserByUsername(String username);

	void addUser(User user);

	void updateUser(User user, int userId);

	void deleteUser(int userId);

	User getUserByUserId(int userId);

	/**
	 * @return list of Users
	 */
	List<User> getAllUsers();
}
