package com.ishanitech.ipalika.service;

import java.util.List;

import com.ishanitech.ipalika.dto.AgriculturalFarmDTO;
import com.ishanitech.ipalika.dto.BeekeepingDTO;
import com.ishanitech.ipalika.model.ExtraReport;
import com.ishanitech.ipalika.model.PopulationReport;
import com.ishanitech.ipalika.model.QuestionReport;

public interface ReportService {
	public void generateReport();
	public List<PopulationReport> getAllPopulationReports();
	public List<QuestionReport> getAllQuestionReports();
	public List<ExtraReport> getExtraReports();
	public List<BeekeepingDTO> getBeekeepingInfo();
	public List<AgriculturalFarmDTO> getAgriculturaFarmInfo();
}
