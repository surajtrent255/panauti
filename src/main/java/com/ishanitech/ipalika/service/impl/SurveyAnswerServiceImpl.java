package com.ishanitech.ipalika.service.impl;

import java.util.List;

import org.jdbi.v3.core.JdbiException;
import org.springframework.stereotype.Service;

import com.ishanitech.ipalika.converter.impl.SurveyAnswerConverter;
import com.ishanitech.ipalika.converter.impl.SurveyAnswerInfoConverter;
import com.ishanitech.ipalika.dao.SurveyAnswerDAO;
import com.ishanitech.ipalika.dto.RequestDTO;
import com.ishanitech.ipalika.dto.SurveyAnswerDTO;
import com.ishanitech.ipalika.dto.SurveyExtraInfoDTO;
import com.ishanitech.ipalika.exception.CustomSqlException;
import com.ishanitech.ipalika.model.SurveyAnswer;
import com.ishanitech.ipalika.model.SurveyAnswerInfo;
import com.ishanitech.ipalika.service.DbService;
import com.ishanitech.ipalika.service.SurveyAnswerService;

import lombok.extern.slf4j.Slf4j;

/**
 * {@code SurveyAnswerServiceImpl} is an implementation class of {@code SurveyAnswerServie} interface, which implements
 * all the business logics related to survey and it's answers. 
 * @author <b> Umesh Bhujel
 * @since 1.0
 */

@Service
public class SurveyAnswerServiceImpl implements SurveyAnswerService {
	private final DbService dbService;
	
	public SurveyAnswerServiceImpl(DbService dbService) {
		this.dbService = dbService;
	}

	@Override
	public void addSurveyAnswers(RequestDTO<List<SurveyAnswerDTO>, List<SurveyExtraInfoDTO>> surveyAnswerInfo) {
		SurveyAnswerConverter surveyAnswerConverter = new SurveyAnswerConverter();
		SurveyAnswerInfoConverter surveyAnswerInfoConverter = new SurveyAnswerInfoConverter();
		List<SurveyAnswerInfo> surveyAnswerInfos = surveyAnswerInfoConverter.fromDto(surveyAnswerInfo.getInfoData());
		List<SurveyAnswer> surveyAnswers = surveyAnswerConverter.fromSurveyAnswerDtos(surveyAnswerInfo.getData());
		try {
			dbService.getDao(SurveyAnswerDAO.class).insertSurveyAnswer(surveyAnswerInfos, surveyAnswers);
		} catch(JdbiException jex) {
			throw new CustomSqlException("Exception: " + jex.getMessage());
		}
	}

}
