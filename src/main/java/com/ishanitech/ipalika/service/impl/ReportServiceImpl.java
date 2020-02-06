/**
 * @author Umesh Bhujel <yoomesbhujel@gmail.com>
 * Since Feb 5, 2020
 */
package com.ishanitech.ipalika.service.impl;


import org.springframework.stereotype.Service;

import com.ishanitech.ipalika.dao.ReportDAO;
import com.ishanitech.ipalika.service.DbService;
import com.ishanitech.ipalika.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	private final DbService dbService;
	
	public ReportServiceImpl(DbService dbService) {
		this.dbService = dbService;
	}

	@Override
	public void generateReport() {
		dbService.getDao(ReportDAO.class).generateReport();
	}

}
