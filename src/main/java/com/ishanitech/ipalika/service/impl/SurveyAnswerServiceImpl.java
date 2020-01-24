package com.ishanitech.ipalika.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jdbi.v3.core.JdbiException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ishanitech.ipalika.converter.impl.AnswerConverter;
import com.ishanitech.ipalika.converter.impl.SurveyAnswerConverter;
import com.ishanitech.ipalika.dao.SurveyAnswerDAO;
import com.ishanitech.ipalika.dto.AnswerDTO;
import com.ishanitech.ipalika.dto.RequestDTO;
import com.ishanitech.ipalika.dto.ResidentDTO;
import com.ishanitech.ipalika.dto.SurveyAnswerDTO;
import com.ishanitech.ipalika.exception.CustomSqlException;
import com.ishanitech.ipalika.exception.FileStorageException;
import com.ishanitech.ipalika.model.Answer;
import com.ishanitech.ipalika.model.SurveyAnswer;
import com.ishanitech.ipalika.service.DbService;
import com.ishanitech.ipalika.service.SurveyAnswerService;
import com.ishanitech.ipalika.utils.FileUtilService;

import lombok.extern.slf4j.Slf4j;

/**
 * {@code SurveyAnswerServiceImpl} is an implementation class of {@code SurveyAnswerServie} interface, which implements
 * all the business logics related to survey and it's answers. 
 * @author <b> Umesh Bhujel
 * @since 1.0
 */

@Slf4j
@Service
public class SurveyAnswerServiceImpl implements SurveyAnswerService {
	private final DbService dbService;
	private final FileUtilService fileUtilService;
	
	public SurveyAnswerServiceImpl(DbService dbService, FileUtilService fileUtilService) {
		this.dbService = dbService;
		this.fileUtilService = fileUtilService;
	}

	@Override
	public void addSurveyAnswers(RequestDTO<List<SurveyAnswerDTO>, Object> surveyAnswerInfo) {
		SurveyAnswerConverter surveyAnswerConverter = new SurveyAnswerConverter();
		List<String> filledIdsInDatabase = dbService.getDao(SurveyAnswerDAO.class).getAllFilledIds();
		List<SurveyAnswer> surveyAnswers = surveyAnswerConverter
				.fromDto(surveyAnswerInfo.getData());
		/*
		 * if(filledIdsInDatabase != null && filledIdsInDatabase.size() > 0) {
		 * surveyAnswers = surveyAnswers .stream() .filter(surveyAnswer ->
		 * !filledIdsInDatabase.contains(surveyAnswer.getFilledId()))
		 * .collect(Collectors.toList()); }
		 */
		
		try {
			//dbService.getDao(SurveyAnswerDAO.class).insertSurveyAnswer(surveyAnswers);
			dbService.getDao(SurveyAnswerDAO.class).addAnswerList(surveyAnswerConverter.fromSuveyAnswersToAnswersList(surveyAnswers));
		} catch(JdbiException jex) {
			throw new CustomSqlException("Exception: " + jex.getMessage());
		}
	}
	
	@Override
	public void addSurveyAnswerImage(MultipartFile image) throws FileStorageException {
		fileUtilService.storeFile(image);
	}

	/**
	 * Adds answer to answer table.
	 * Answer table is the latest table which replaces the old survey_answer table.
	 */
	@Override
	public void addAnswers(List<AnswerDTO> answersRequest) {
		List<String> filledIdsInDatabase = dbService.getDao(SurveyAnswerDAO.class).getAllFilledIds();
		List<Answer> answers = new AnswerConverter().fromDto(answersRequest)
				.stream()
				.filter(answer -> !filledIdsInDatabase.contains(answer.getFilledId()))
				.collect(Collectors.toList());
		
		try {
			dbService.getDao(SurveyAnswerDAO.class).addAnswerList(answers);
		} catch(JdbiException jex) {
			throw new CustomSqlException("Exception: " + jex.getMessage());
		}
	}

	@Override
	public List<ResidentDTO> getResident() {
		List<ResidentDTO> residents = new ArrayList<>();
		try {
			List<Answer> residentsAllInfo = dbService.getDao(SurveyAnswerDAO.class).getResidents();
			residents = new AnswerConverter().entityListToResidentList(residentsAllInfo);
			return residents;
		} catch(JdbiException jex) {
			throw new CustomSqlException("Exception: " +jex.getLocalizedMessage());
		}
	}

	@Override
	public Answer getAnswerByFilledId(String filledId) {
		try {
			return dbService.getDao(SurveyAnswerDAO.class).getAnswerByFilledId(filledId);
		} catch(JdbiException jex) {
			throw new CustomSqlException("Exception: " +jex.getLocalizedMessage());
		}
	}

}
