package com.ishanitech.ipalika.service.impl;



import org.jdbi.v3.core.JdbiException;
import org.springframework.stereotype.Service;

import com.ishanitech.ipalika.dao.FormDAO;
import com.ishanitech.ipalika.exception.CustomSqlException;
import com.ishanitech.ipalika.exception.EntityNotFoundException;
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

	/**
	 * A method of {@code FormService} to insert form into table.
	 * @param form Form object
	 * @throws CustomSqlException if exceptions is thrown while executing sql query.
	 * @return void
	 * @author <b> Umesh Bhujel </b>
	 * @since 1.0
	 */
	@Override
	public void addForm(Form form) throws CustomSqlException {
		FormDAO formDAO = dbService.getDao(FormDAO.class);
		try {
			formDAO.addForm(form);
		} catch(JdbiException jex) {
			throw new CustomSqlException("Something went wrong while inserting form data.");
		}
	}


	
	/**
	 * Gets Form By Id.
	 * @param id integer form id
	 * @throws EntityNotFoundException if no form is found with the given id.
	 * @return Form object
	 * @author <b> Umesh Bhujel </b>
	 * @since 1.0
	 */
	@Override
	public Form getFormById(Integer id) throws EntityNotFoundException {
		FormDAO formDAO = dbService.getDao(FormDAO.class);
		try {
			Form form = formDAO.getFormById(id);
			if(form != null) {
				return form;
			}
			
			throw new EntityNotFoundException("No form found!");
		} catch(JdbiException jex) {
			throw new CustomSqlException("Something went wrong while getting data from datebase. This may be server problem, contact your server admin.");
		}
	}

}
