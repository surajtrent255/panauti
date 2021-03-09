package com.ishanitech.ipalika.service.impl;


import java.util.ArrayList;
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
import com.ishanitech.ipalika.utils.ReportUtil;

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
		List<PopulationReport> pReports = reportWardDAO.getAllPopulationReports(wardNo);
		
		//List<PopulationReport> newReports = new ArrayList<PopulationReport>();
				
		for(PopulationReport report : pReports) {
			report.setOption1((double)((int) (report.getOption1() * ReportUtil.PIF)));
			report.setOption2((double)((int) (report.getOption2() * ReportUtil.PIF)));
			report.setOption3((double)((int) (report.getOption3() * ReportUtil.PIF)));
			report.setOption4((double)((int) (report.getOption4() * ReportUtil.PIF)));
			report.setOption5((double)((int) (report.getOption5() * ReportUtil.PIF)));
			report.setOption6((double)((int) (report.getOption6() * ReportUtil.PIF)));
			report.setOption7((double)((int) (report.getOption7() * ReportUtil.PIF)));
			report.setOption8((double)((int) (report.getOption8() * ReportUtil.PIF)));
			report.setOption9((double)((int) (report.getOption9() * ReportUtil.PIF)));
			report.setOption10((double)((int) (report.getOption10() * ReportUtil.PIF)));
			report.setOption11((double)((int) (report.getOption11() * ReportUtil.PIF)));
			report.setOption12((double)((int) (report.getOption12() * ReportUtil.PIF)));
			report.setOption13((double)((int) (report.getOption13() * ReportUtil.PIF)));
			report.setOption14((double)((int) (report.getOption14() * ReportUtil.PIF)));
			report.setOption15((double)((int) (report.getOption15() * ReportUtil.PIF)));
			
			report.setTotal((double)((int) (report.getTotal() * ReportUtil.PIF)));
			
			
		}
		
		
		return pReports;
				
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
		if(wardNo == 0) {
			return reportDAO.getBeekeepingInfo();
		} else {
			return reportWardDAO.getBeekeepingInfo(wardNo);
		}
	}

	@Override
	public List<AgriculturalFarmDTO> getAgriculturaFarmInfo(int wardNo) {
		if(wardNo == 0) {
			return reportDAO.getAgriculturalFarmInfo();
		} else {
			return reportWardDAO.getAgriculturalFarmInfo(wardNo);
		}
	}

	@Override
	public List<FavouritePlaceReport> getFavouritePlaceReports(int wardNo) {
		return reportWardDAO.getAllFavouritePlaceReports(wardNo);
	}

}
