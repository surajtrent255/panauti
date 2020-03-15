package com.ishanitech.ipalika.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ishanitech.ipalika.dto.AnswerDTO;
import com.ishanitech.ipalika.dto.RequestDTO;
import com.ishanitech.ipalika.dto.ResidentDTO;
import com.ishanitech.ipalika.dto.ResidentDetailDTO;
import com.ishanitech.ipalika.dto.RoleWardDTO;
import com.ishanitech.ipalika.dto.SurveyAnswerDTO;
import com.ishanitech.ipalika.dto.SurveyAnswerExtraInfoDTO;
import com.ishanitech.ipalika.model.Answer;

public interface SurveyAnswerService {
	public List<ResidentDTO> getResident(RoleWardDTO roleWardDTO);
	public List<ResidentDTO> searchResident(String searchKey, String wardNo);
	public List<ResidentDTO> searchWardResident(String wardNo);
	public ResidentDetailDTO getAnswerByFilledId(String filledId);
	public void addAnswers(List<AnswerDTO> answers);
	public void updateAnswers(AnswerDTO answer);
	public void addSurveyAnswers(RequestDTO<List<SurveyAnswerDTO>, List<SurveyAnswerExtraInfoDTO>> surveyAnswerInfo);
	public void addSurveyAnswerImage(MultipartFile image);
	public Answer getRawAnswerByFilledId(String filledId);
	public void updateSurveyAnswerImage(MultipartFile image);
	
}
