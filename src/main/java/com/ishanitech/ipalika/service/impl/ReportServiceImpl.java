package com.ishanitech.ipalika.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.ishanitech.ipalika.dao.ReportDAO;
import com.ishanitech.ipalika.dao.ReportWardDAO;
import com.ishanitech.ipalika.dto.AgriculturalFarmDTO;
import com.ishanitech.ipalika.dto.BeekeepingDTO;
import com.ishanitech.ipalika.model.ExtraReport;
import com.ishanitech.ipalika.model.FavouritePlaceReport;
import com.ishanitech.ipalika.model.PopulationReport;
import com.ishanitech.ipalika.model.QuestionReport;
import com.ishanitech.ipalika.service.DbService;
import com.ishanitech.ipalika.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	private final ReportDAO reportDAO;
	private final ReportWardDAO reportWardDAO;

	public ReportServiceImpl(DbService dbService) {
		this.reportDAO = dbService.getDao(ReportDAO.class);
		this.reportWardDAO = dbService.getDao(ReportWardDAO.class);
	}

	@Override
	public void generateReport(int wardNo) {
		if(wardNo == 0) {
			reportDAO.generateReport();
		} else {
			reportWardDAO.generateReport(wardNo);
		}
	}

	@Override
	public List<PopulationReport> getAllPopulationReports(int wardNo) {
		return reportWardDAO.getAllPopulationReports(wardNo);
	}

	@Override
	public List<QuestionReport> getAllQuestionReports(int wardNo) {
		return reportWardDAO.getAllQuestionReport(wardNo);
	}

	@Override
	public List<ExtraReport> getExtraReports(int wardNo) {
		return reportWardDAO.getExtraReports(wardNo);
	}

	@Override
	public List<BeekeepingDTO> getBeekeepingInfo(int wardNo) {
		return reportWardDAO.getBeekeepingInfo(wardNo);
	}

	@Override
	public List<AgriculturalFarmDTO> getAgriculturaFarmInfo(int wardNo) {
		return reportWardDAO.getAgriculturalFarmInfo(wardNo);
	}

	@Override
	public List<FavouritePlaceReport> getFavouritePlaceReports(int wardNo) {
		return reportWardDAO.getAllFavouritePlaceReports(wardNo);
	}

}
