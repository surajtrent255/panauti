package com.ishanitech.ipalika.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ishanitech.ipalika.dto.ResidentDTO;
import com.ishanitech.ipalika.model.Answer;
import com.ishanitech.ipalika.service.SurveyAnswerService;

@RequestMapping("/resident")
@RestController
public class ResidentController {
	private final SurveyAnswerService surveyAnswerSwervice;
	public ResidentController(SurveyAnswerService surveyAnswerSwervice) {
		this.surveyAnswerSwervice = surveyAnswerSwervice;
	}


	@GetMapping
	public List<ResidentDTO> getResidents() {
		return surveyAnswerSwervice.getResident();
	}
	
	//returns full information of the resident by its filled id.
	@GetMapping("/detail/{filledId}")
	public Answer getFullInformationOfResident(Model model, @PathVariable("filledId") String filledId) {
		return surveyAnswerSwervice.getAnswerByFilledId(filledId);
	}
}
