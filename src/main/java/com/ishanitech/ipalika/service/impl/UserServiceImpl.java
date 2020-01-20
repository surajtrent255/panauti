package com.ishanitech.ipalika.service.impl;



import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ishanitech.ipalika.converter.impl.UserConverter;
import com.ishanitech.ipalika.dao.UserDAO;
import com.ishanitech.ipalika.dto.UserDTO;
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

	@Override
	public void addUser(UserDTO userDto) {
		UserDAO userDao = dbService.getDao(UserDAO.class);
		User user = new UserConverter().fromDto(userDto);
		user.setPassword(encoder.encode(user.getPassword()));
		userDao.addUserAndRole(user);
	}

}
