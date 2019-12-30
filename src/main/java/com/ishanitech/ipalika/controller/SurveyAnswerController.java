package com.ishanitech.ipalika.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ishanitech.ipalika.dto.RequestDTO;
import com.ishanitech.ipalika.dto.SurveyAnswerDTO;
import com.ishanitech.ipalika.dto.SurveyExtraInfoDTO;
import com.ishanitech.ipalika.exception.CustomSqlException;
import com.ishanitech.ipalika.service.SurveyAnswerService;


@RequestMapping("/survey-answer")
@Controller
public class SurveyAnswerController {
	private final SurveyAnswerService surveyAnswerService;
	
	public SurveyAnswerController(SurveyAnswerService surveyAnswerService) {
		this.surveyAnswerService = surveyAnswerService;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void addSurveyAnswer(HttpServletResponse http, @RequestBody RequestDTO<List<SurveyAnswerDTO>, SurveyExtraInfoDTO> surveyAnswerInfo) throws CustomSqlException {
		surveyAnswerService.addSurveyAnswers(surveyAnswerInfo);
	}
}
