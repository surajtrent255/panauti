package com.ishanitech.ipalika.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ishanitech.ipalika.dto.RequestDTO;
import com.ishanitech.ipalika.dto.SurveyAnswerDTO;
import com.ishanitech.ipalika.dto.SurveyExtraInfoDTO;
import com.ishanitech.ipalika.exception.CustomSqlException;
import com.ishanitech.ipalika.service.SurveyAnswerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/survey-answer")
@RestController
public class SurveyAnswerController {
	private final SurveyAnswerService surveyAnswerService;
	
	public SurveyAnswerController(SurveyAnswerService surveyAnswerService) {
		this.surveyAnswerService = surveyAnswerService;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void addSurveyAnswer(HttpServletResponse http, 
			@RequestBody RequestDTO<List<SurveyAnswerDTO>, SurveyExtraInfoDTO> surveyAnswerInfo) throws CustomSqlException {
		log.info("### Add Survey Answer Controller Called ###");
		surveyAnswerService.addSurveyAnswers(surveyAnswerInfo);
	}
}
