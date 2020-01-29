package com.ishanitech.ipalika.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ishanitech.ipalika.dto.AnswerDTO;
import com.ishanitech.ipalika.dto.RequestDTO;
import com.ishanitech.ipalika.dto.SurveyAnswerDTO;
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
			@RequestBody RequestDTO<List<SurveyAnswerDTO>, Object> surveyAnswerInfo) throws CustomSqlException {
		surveyAnswerService.addSurveyAnswers(surveyAnswerInfo);
	}

	/**
	 * This is new endpoint for survey answer insertion operation.
	 * This endpoint expects the list of request object ({@code AnswerDTO}) which has the same format as
	 * {@code Answer} model.
	 * @param http HttpServletResponse object 
	 * @param surveyAnswerInfo request object
	 * @throws CustomSqlException
	 * @author <b> Umesh Bhujel
	 * @since 1.0
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/new")
	public void addSurveyAnswers(HttpServletResponse http,
			@RequestBody RequestDTO<List<AnswerDTO>, Object> surveyAnswerInfo) throws CustomSqlException {
		if (surveyAnswerInfo.getData() != null) {
			surveyAnswerService.addAnswers(surveyAnswerInfo.getData());
		} else {
			throw new NullPointerException("Invalid data");
		}
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/image")
	public void uploadImageForSurveyAnswer(@RequestParam("picture") MultipartFile image) {
		log.info(String.format("Image name: %s", image.getOriginalFilename()));
		surveyAnswerService.addSurveyAnswerImage(image);
	}
}
