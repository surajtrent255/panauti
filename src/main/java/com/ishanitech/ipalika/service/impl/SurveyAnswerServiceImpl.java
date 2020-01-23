package com.ishanitech.ipalika.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.jdbi.v3.core.JdbiException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ishanitech.ipalika.converter.impl.SurveyAnswerConverter;
import com.ishanitech.ipalika.converter.impl.SurveyAnswerInfoConverter;
import com.ishanitech.ipalika.dao.SurveyAnswerDAO;
import com.ishanitech.ipalika.dto.RequestDTO;
import com.ishanitech.ipalika.dto.SurveyAnswerDTO;
import com.ishanitech.ipalika.dto.SurveyExtraInfoDTO;
import com.ishanitech.ipalika.exception.CustomSqlException;
import com.ishanitech.ipalika.exception.FileStorageException;
import com.ishanitech.ipalika.model.SurveyAnswer;
import com.ishanitech.ipalika.model.SurveyAnswerInfo;
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
	public void addSurveyAnswers(RequestDTO<List<SurveyAnswerDTO>, List<SurveyExtraInfoDTO>> surveyAnswerInfo) {
		SurveyAnswerConverter surveyAnswerConverter = new SurveyAnswerConverter();
		SurveyAnswerInfoConverter surveyAnswerInfoConverter = new SurveyAnswerInfoConverter();
		List<String> filledIdsInDatabase = dbService.getDao(SurveyAnswerDAO.class).getAllFilledIds();
		log.info(String.format("Incomming request"));
		List<SurveyAnswerInfo> surveyAnswerInfos = surveyAnswerInfoConverter
				.fromDto(surveyAnswerInfo.getInfoData())
				.stream()
				.filter(surveyAnswer -> !filledIdsInDatabase.contains(surveyAnswer.getFilledId()))
				.collect(Collectors.toList());
		
		List<SurveyAnswer> surveyAnswers = surveyAnswerConverter
				.fromDto(surveyAnswerInfo.getData())
				.stream()
				.filter(surveyAnswer -> !filledIdsInDatabase.contains(surveyAnswer.getFilledId()))
				.collect(Collectors.toList());
		
		//List<Citizen>
		try {
			dbService.getDao(SurveyAnswerDAO.class).insertSurveyAnswer(surveyAnswerInfos, surveyAnswers);
		} catch(JdbiException jex) {
			throw new CustomSqlException("Exception: " + jex.getMessage());
		}
	}
	
	@Override
	public void addSurveyAnswerImage(MultipartFile image) throws FileStorageException {
		fileUtilService.storeFile(image);
	}

}
