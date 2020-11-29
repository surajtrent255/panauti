package com.ishanitech.ipalika.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.ishanitech.ipalika.dao.ReportDAO;
import com.ishanitech.ipalika.dto.AgriculturalFarmDTO;
import com.ishanitech.ipalika.dto.BeekeepingDTO;
import com.ishanitech.ipalika.model.ExtraReport;
import com.ishanitech.ipalika.model.PopulationReport;
import com.ishanitech.ipalika.model.QuestionReport;
import com.ishanitech.ipalika.service.DbService;
import com.ishanitech.ipalika.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	private final ReportDAO reportDAO;
	public ReportServiceImpl(DbService dbService) {
		this.reportDAO = dbService.getDao(ReportDAO.class);
	}

	@Override
	public void generateReport() {
		reportDAO.generateReport();
	}

	@Override
	public List<PopulationReport> getAllPopulationReports() {
		return reportDAO.getAllPopulationReports();
	}

	@Override
	public List<QuestionReport> getAllQuestionReports() {
		return reportDAO.getAllQuestionReport();
	}

	@Override
	public List<ExtraReport> getExtraReports() {
		return reportDAO.getExtraReports();
	}

	@Override
	public List<BeekeepingDTO> getBeekeepingInfo() {
		return reportDAO.getBeekeepingInfo();
	}

	@Override
	public List<AgriculturalFarmDTO> getAgriculturaFarmInfo() {
		return reportDAO.getAgriculturalFarmInfo();
	}

}
