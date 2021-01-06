package com.ishanitech.ipalika.service;

import java.util.List;

import com.ishanitech.ipalika.dto.AgriculturalFarmDTO;
import com.ishanitech.ipalika.dto.BeekeepingDTO;
import com.ishanitech.ipalika.model.ExtraReport;
import com.ishanitech.ipalika.model.FavouritePlaceReport;
import com.ishanitech.ipalika.model.PopulationReport;
import com.ishanitech.ipalika.model.QuestionReport;

public interface ReportService {
	public void generateReport(int wardNo);
	public List<PopulationReport> getAllPopulationReports(int wardNo);
	public List<QuestionReport> getAllQuestionReports(int wardNo);
	public List<ExtraReport> getExtraReports(int wardNo);
	public List<BeekeepingDTO> getBeekeepingInfo(int wardNo);
	public List<AgriculturalFarmDTO> getAgriculturaFarmInfo(int wardNo);
	public List<FavouritePlaceReport> getFavouritePlaceReports(int wardNo);
}
