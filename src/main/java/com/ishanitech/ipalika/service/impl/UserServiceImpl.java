package com.ishanitech.ipalika.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jdbi.v3.core.mapper.JoinRow;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ishanitech.ipalika.dao.UserDAO;
import com.ishanitech.ipalika.exception.EntityNotFoundException;
import com.ishanitech.ipalika.model.Role;
import com.ishanitech.ipalika.model.User;
import com.ishanitech.ipalika.service.DbService;
import com.ishanitech.ipalika.service.UserService;

import lombok.extern.slf4j.Slf4j;

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
	public User getUserByUsername(String username) {
		UserDAO userDao = dbService.getDao(UserDAO.class);
		return userDao.getUserByUsername(username);
	}
	
	@Override
	public User getUserByUserId(int userId) {
		UserDAO userDao = dbService.getDao(UserDAO.class);
		List<JoinRow> userRow = userDao.getUserByUserId(userId);
		if (userRow != null && userRow.size() > 0) {
			User user = userRow.get(0).get(User.class);
			Set<Role> roles = new HashSet<>();
			userRow.forEach(jr -> {
				roles.add(jr.get(Role.class));
			});

			user.setRole(roles);
			return user;
		} else {
			throw new EntityNotFoundException("No user found");
		}
	}

	@Override
	@Transactional
	public void addUser(User user) {
		UserDAO userDao = dbService.getDao(UserDAO.class);
		user.setPassword(encoder.encode(user.getPassword()));
		int userId = userDao.addUser(user);
		if(user.getRole() != null) {
			userDao.addUserRoles(userId, user.getRole());
		} else {
			userDao.addUserRole(userId, 2); //role 2 for customer role
		}
	}

	@Override
	public void updateUser(User user, int userId) {
		user.setPassword(encoder.encode(user.getPassword()));
		UserDAO userDao = dbService.getDao(UserDAO.class);
		userDao.updateUser(user, userId);
	}

	@Override
	@Transactional
	public void deleteUser(int userId) {
		UserDAO userDao = dbService.getDao(UserDAO.class);
		userDao.deleteUser(userId);
		userDao.deleteUserRole(userId);
	}

	@Override
	public List<User> getAllUsers() {
		UserDAO userDao = dbService.getDao(UserDAO.class);
		List<JoinRow> userRow = userDao.getAllUsers();
		List<User> users = new ArrayList<>();
		if (userRow != null && userRow.size() > 0) {
			User user = new User();
			Set<Role> roles = new HashSet<>();
			for(JoinRow jrs : userRow) {
				
				if(user.getId() == jrs.get(User.class).getId()) {
					roles.add(jrs.get(Role.class));
					continue;
				} else {
					roles = new HashSet<>();
					user = jrs.get(User.class);
					roles.add(jrs.get(Role.class));
					user.setRole(roles);
				}
				
				users.add(user);
			}
			
			
		} else {
			throw new EntityNotFoundException("No users found");
		}
		
		return users;
	}



}
