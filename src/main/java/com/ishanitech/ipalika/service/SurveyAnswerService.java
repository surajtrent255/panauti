package com.ishanitech.ipalika.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ishanitech.ipalika.dto.AnswerDTO;
import com.ishanitech.ipalika.dto.RequestDTO;
import com.ishanitech.ipalika.dto.ResidentDTO;
import com.ishanitech.ipalika.dto.SurveyAnswerDTO;

public interface SurveyAnswerService {
	public List<ResidentDTO> getResident();
	public void addAnswers(List<AnswerDTO> answers);
	public void addSurveyAnswers(RequestDTO<List<SurveyAnswerDTO>, Object> surveyAnswerInfo);
	public void addSurveyAnswerImage(MultipartFile image);
}
