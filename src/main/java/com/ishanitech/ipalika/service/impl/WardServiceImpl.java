/**
 * @author Umesh Bhujel <yoomesbhujel@gmail.com>
 * Since Feb 18, 2020
 */
package com.ishanitech.ipalika.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ishanitech.ipalika.dao.WardDAO;
import com.ishanitech.ipalika.service.DbService;
import com.ishanitech.ipalika.servicer.WardService;

@Service
public class WardServiceImpl implements WardService {
	private final DbService dbService;
	
	public WardServiceImpl(DbService dbService) {
		this.dbService = dbService;
	}

	@Override
	public List<Integer> getAllWardNumbers() {
		 return dbService.getDao(WardDAO.class).getAllWardNumbers();
	}

}
