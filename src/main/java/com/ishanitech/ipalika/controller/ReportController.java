
package com.ishanitech.ipalika.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ishanitech.ipalika.dto.ResponseDTO;
import com.ishanitech.ipalika.model.PopulationReport;
import com.ishanitech.ipalika.model.QuestionReport;
import com.ishanitech.ipalika.service.ReportService;

@RequestMapping("/report")
@RestController
public class ReportController {
	private final ReportService reportService;
	
	public ReportController(ReportService reportService) {
		this.reportService = reportService;
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void generateReport() {
		reportService.generateReport();
	}
	
	@GetMapping("/population")
	ResponseDTO<List<PopulationReport>> getPopulationReport() {
		return new ResponseDTO<List<PopulationReport>>(reportService.getAllPopulationReports());
	}
	
	@GetMapping("/question")
	ResponseDTO<List<QuestionReport>> getQuestionReports() {
		return new ResponseDTO<List<QuestionReport>>(reportService.getAllQuestionReports());
	}
}
