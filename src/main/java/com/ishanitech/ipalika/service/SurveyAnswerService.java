package com.ishanitech.ipalika.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ishanitech.ipalika.dto.RequestDTO;
import com.ishanitech.ipalika.dto.SurveyAnswerDTO;
import com.ishanitech.ipalika.dto.SurveyExtraInfoDTO;

public interface SurveyAnswerService {
	public void addSurveyAnswers(RequestDTO<List<SurveyAnswerDTO>, List<SurveyExtraInfoDTO>> surveyAnswerInfo);
	public void addSurveyAnswerImage(MultipartFile image);
}
