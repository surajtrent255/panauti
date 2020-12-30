
package com.ishanitech.ipalika.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ishanitech.ipalika.dto.AgriculturalFarmDTO;
import com.ishanitech.ipalika.dto.BeekeepingDTO;
import com.ishanitech.ipalika.dto.ResponseDTO;
import com.ishanitech.ipalika.exception.CustomSqlException;
import com.ishanitech.ipalika.model.ExtraReport;
import com.ishanitech.ipalika.model.FavouritePlaceReport;
import com.ishanitech.ipalika.model.PopulationReport;
import com.ishanitech.ipalika.model.QuestionReport;
import com.ishanitech.ipalika.service.ReportService;


/**
 * {@code ReportController} handles the request for Report.
 * @author <b> Umesh Bhujel
 * @since 1.0
 */
@RequestMapping("/report")
@RestController
public class ReportController {
	private final ReportService reportService;
	
	public ReportController(ReportService reportService) {
		this.reportService = reportService;
	}
	
	
	/**
	 * Generates Report
	 * @author <b> Umesh Bhujel </b>
	 * @since 1.0
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void generateReport() {
		reportService.generateReport();
	}
	
	
	/**
	 * Gets population report
	 * @return {@code ResponseDTO<List<PopulationReport>} object
	 * @author <b> Umesh Bhujel </b>
	 * @since 1.0
	 */
	@GetMapping("/population")
	ResponseDTO<List<PopulationReport>> getPopulationReport() {
		return new ResponseDTO<List<PopulationReport>>(reportService.getAllPopulationReports());
	}
	
	
	/**
	 * Gets question report
	 * @return {@code ResponseDTO<List<QuestionReport>} object
	 * @author <b> Umesh Bhujel </b>
	 * @since 1.0
	 */
	@GetMapping("/question")
	ResponseDTO<List<QuestionReport>> getQuestionReports() {
		return new ResponseDTO<List<QuestionReport>>(reportService.getAllQuestionReports());
	}
	
	
	/**
	 * Gets Extra report
	 * @return {@code ResponseDTO<List<ExtraReport>} object
	 * @author <b> Umesh Bhujel </b>
	 * @since 1.0
	 */
	@GetMapping("/extra")
	ResponseDTO<List<ExtraReport>> getExtraReports() {
		return new ResponseDTO<>(reportService.getExtraReports());
	}
	
	
	@GetMapping("/favouritePlace")
	ResponseDTO<List<FavouritePlaceReport>> getFavouritePlaceReports() {
		return new ResponseDTO<>(reportService.getFavouritePlaceReports());
	}
	
	@GetMapping("/beekeeping") 
	public ResponseDTO<List<BeekeepingDTO>> getBeekeepingInfo() throws CustomSqlException {
		return new ResponseDTO<List<BeekeepingDTO>>(reportService.getBeekeepingInfo());
	}
	
	
	@GetMapping("/agriculturalFarm") 
	public ResponseDTO<List<AgriculturalFarmDTO>> getAgriculturalFarmInfo() throws CustomSqlException {
		return new ResponseDTO<List<AgriculturalFarmDTO>>(reportService.getAgriculturaFarmInfo());
	}
	
}