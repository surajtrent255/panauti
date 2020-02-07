package com.ishanitech.ipalika.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.ishanitech.ipalika.dao.ReportDAO;
import com.ishanitech.ipalika.model.PopulationReport;
import com.ishanitech.ipalika.model.QuestionReport;
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

	@Override
	public List<PopulationReport> getAllPopulationReports() {
		return dbService.getDao(ReportDAO.class).getAllPopulationReports();
	}

	@Override
	public List<QuestionReport> getAllQuestionReports() {
		return dbService.getDao(ReportDAO.class).getAllQuestionReport();
	}

}
