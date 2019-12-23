/**
 * @author Umesh Bhujel <yoomesbhujel@gmail.com>
 * Since Dec 23, 2019
 */
package com.ishanitech.ipalika.service.impl;


import org.jdbi.v3.core.JdbiException;
import org.springframework.stereotype.Service;

import com.ishanitech.ipalika.dao.FormDAO;
import com.ishanitech.ipalika.exception.CustomSqlException;
import com.ishanitech.ipalika.model.Form;
import com.ishanitech.ipalika.service.DbService;
import com.ishanitech.ipalika.service.FormService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FormServiceImpl implements FormService {
	private DbService dbService;
	public FormServiceImpl(DbService dbService) {
		this.dbService = dbService;
	}


	@Override
	public void addForm(Form form) throws CustomSqlException {
		FormDAO formDAO = dbService.getDao(FormDAO.class);
		try {
			formDAO.addForm(form);
		} catch(JdbiException jex) {
			throw new CustomSqlException("Something went wrong while inserting form data.");
		}
	}

}
