package com.ishanitech.ipalika.service.impl;



import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;

import org.jdbi.v3.core.JdbiException;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ishanitech.ipalika.converter.impl.UserConverter;
import com.ishanitech.ipalika.dao.UserDAO;
import com.ishanitech.ipalika.dto.UserDTO;
import com.ishanitech.ipalika.exception.EntityNotFoundException;
import com.ishanitech.ipalika.model.User;
import com.ishanitech.ipalika.service.DbService;
import com.ishanitech.ipalika.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * {@code UserServiceImpl} is an implementation class of 
 * {@link com.ishanitech.ipalika.service.UserService} interface which does all the 
 * user related CRUD operations.
 * @author Umesh Bhujel
 * @since 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
	private DbService dbService;
	private PasswordEncoder encoder;

	public UserServiceImpl(DbService dbService, PasswordEncoder encoder) {
		this.dbService = dbService;
		this.encoder = encoder;
	}

	@Override
	public User getUserByUsername(String username) throws UsernameNotFoundException {
		UserDAO userDao = dbService.getDao(UserDAO.class);
		User user = null;
		try {
			user = userDao.getUserByUsername(username);
		} catch(UnableToExecuteStatementException ex) {
			log.info("Exception occured: " + ex.getMessage());
		}
		
		if(user != null) {
			return user;
		}
		
		throw new UsernameNotFoundException("Incorrect Credentials!");
	}

	@Secured({"ROLE_SUPER_ADMIN", "ROLE_CENTRAL_ADMIN"})
	@Override
	public void addUser(UserDTO userDto) {
		UserDAO userDao = dbService.getDao(UserDAO.class);
		User user = new UserConverter().fromDto(userDto);
		user.setPassword(encoder.encode(user.getPassword()));
		 try {
			 userDao.addUserAndRole(user);
		 } catch (JdbiException jex) {
			 log.error(String.format("Error occured while inserting a user: %s", jex.getLocalizedMessage()));
		 }
	}

	@Secured({"ROLE_SUPER_ADMIN", "ROLE_CENTRAL_ADMIN"})
	@Override
	public void deleteUser(int userId) {
		try {
			dbService.getDao(UserDAO.class).deleteUser(userId);
		} catch (JdbiException jex) {
			log.error(String.format("Error occured while deleting a user: userId%d %s", userId, jex.getLocalizedMessage()));
		}
	}

	@PreAuthorize("#userId == authentication.principal.user.userId")
	@Override
	public void updateUserInfoByUserId(Map<String, Object> user, int userId) {
		try {
			User userInfo = this.getUserById(userId);
			if(user.containsKey("mobileNumber")) {
				userInfo.setMobileNumber((String)user.get("mobileNumber"));
			}
			
			if(user.containsKey("username")) {
				userInfo.setUsername((String)user.get("username"));
			}
			
			if(user.containsKey("fullName")) {
				String[] fullName = ((String)user.get("fullName")).split(" ");
				
				if(fullName.length > 0 && fullName.length == 1) {
					userInfo.setFirstName(fullName[0]);
				}
				
				if(fullName.length > 0 && fullName.length == 2) {
					userInfo.setFirstName(fullName[0]);
					userInfo.setLastName(fullName[1]);
				}
				
				if(fullName.length > 0 && fullName.length == 3) {
					userInfo.setFirstName(fullName[0]);
					userInfo.setMiddleName(fullName[1]);
					userInfo.setLastName(fullName[2]);
				}
			}
			dbService.getDao(UserDAO.class).updateUserInfo(userInfo, userId);
		} catch(JdbiException jex) {
			log.error(String.format("Error occured while updating userinfo %s", jex.getLocalizedMessage()));
		}
	}

	@Secured({"SUPER_ADMIN", "CENTRAL_ADMIN"})
	@Override
	public void disableUser(int userId) {
		try {
			dbService.getDao(UserDAO.class).disableUser(userId);
		} catch (JdbiException jex) {
			log.error(String.format("Error occured while disabling a user: userId%d %s", userId, jex.getLocalizedMessage()));
		}
	}

	@PreAuthorize("#userId == authentication.principal.userId")
	@Override
	public void changePassword(String newPassword, int userId) {
		String encryptedPassword = this.encoder.encode(newPassword);
		try {
			dbService.getDao(UserDAO.class).changePassword(encryptedPassword, userId);
		} catch(JdbiException jex) {
			log.error(String.format("Something went wrong while changing user password: %s", jex.getLocalizedMessage()));
		}
	}

	@Override
	public User getUserById(int userId) {
		User user = null;
		try {
			user = dbService.getDao(UserDAO.class).getUserById(userId);
		} catch(JdbiException jex) {
			log.error(String.format("Something went wrong while getting userinfo %s", jex.getLocalizedMessage()));
		}
		
		if(user != null) {
			return user; 
		}
		
		throw new EntityNotFoundException("User not found");
	}

}
