/**
 * @author Umesh Bhujel <yoomesbhujel@gmail.com>
 * Since Dec 20, 2019
 */
package com.ishanitech.ipalika.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ishanitech.ipalika.dao.FormDetailDAO;
import com.ishanitech.ipalika.dao.OptionDao;
import com.ishanitech.ipalika.model.FormDetail;
import com.ishanitech.ipalika.service.DbService;
import com.ishanitech.ipalika.service.FormDetailService;

@Service
public class FormDetailServiceImpl implements FormDetailService {
	DbService dbService;
	
	public FormDetailServiceImpl(DbService dbService) {
		this.dbService = dbService;
	}

	
	@Override
	@Transactional
	public List<FormDetail> getFormDetailById(Integer formId) {	
		FormDetailDAO dao = dbService.getDao(FormDetailDAO.class);
		OptionDao optionDao = dbService.getDao(OptionDao.class);
		List<FormDetail> formDetails = dao.getAllFormDetails(formId);
		formDetails.forEach(formDetail -> {
			formDetail.setOptions(optionDao.getAllOptionByQuestionId(formDetail.getQuestionId()));
		});
		
		return formDetails;
	}

}
