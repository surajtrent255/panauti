package com.ishanitech.ipalika.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.jdbi.v3.core.JdbiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ishanitech.ipalika.config.properties.RestBaseProperty;
import com.ishanitech.ipalika.converter.impl.AnswerConverter;
import com.ishanitech.ipalika.converter.impl.SurveyAnswerConverter;
import com.ishanitech.ipalika.dao.DistrictDAO;
import com.ishanitech.ipalika.dao.SurveyAnswerDAO;
import com.ishanitech.ipalika.dao.UserDAO;
import com.ishanitech.ipalika.dto.AnswerDTO;
import com.ishanitech.ipalika.dto.FamilyMemberDTO;
import com.ishanitech.ipalika.dto.RequestDTO;
import com.ishanitech.ipalika.dto.ResidentDTO;
import com.ishanitech.ipalika.dto.ResidentDetailDTO;
import com.ishanitech.ipalika.dto.SurveyAnswerDTO;
import com.ishanitech.ipalika.dto.SurveyAnswerExtraInfoDTO;
import com.ishanitech.ipalika.exception.CustomSqlException;
import com.ishanitech.ipalika.exception.FileStorageException;
import com.ishanitech.ipalika.model.Answer;
import com.ishanitech.ipalika.model.QuestionOption;
import com.ishanitech.ipalika.model.SurveyAnswer;
import com.ishanitech.ipalika.service.DbService;
import com.ishanitech.ipalika.service.ResidentService;
import com.ishanitech.ipalika.service.SurveyAnswerService;
import com.ishanitech.ipalika.utils.FileUtilService;
import com.ishanitech.ipalika.utils.ImageUtilService;

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
	private final ResidentService residentService;
	
	@Autowired
	private RestBaseProperty restUrlProperty;
	
	public SurveyAnswerServiceImpl(DbService dbService, FileUtilService fileUtilService, ResidentService residentService) {
		this.dbService = dbService;
		this.fileUtilService = fileUtilService;
		this.residentService = residentService;
	}

	@Override
	public void addSurveyAnswers(RequestDTO<List<SurveyAnswerDTO>, List<SurveyAnswerExtraInfoDTO>> surveyAnswerInfo) {
		SurveyAnswerConverter surveyAnswerConverter = new SurveyAnswerConverter();
		List<String> filledIdsInDatabase = dbService.getDao(SurveyAnswerDAO.class).getAllFilledIds();
		List<SurveyAnswer> surveyAnswers = surveyAnswerConverter
				.fromDto(surveyAnswerInfo.getData())
				.stream()
				.filter(surveyAns -> !filledIdsInDatabase.contains(surveyAns.getFilledId()))
				.collect(Collectors.toList());
		List<SurveyAnswerExtraInfoDTO> surveyExtraInfo = surveyAnswerInfo.getInfoData().stream()
				.filter(extraData -> !filledIdsInDatabase.contains(extraData.getFilledFormId()))
				.collect(Collectors.toList());
		try {
			List<Answer> answers = surveyAnswerConverter.fromSuveyAnswersToAnswersList(surveyAnswers);
			IntStream.range(0, answers.size())
				.forEach(i -> {
					answers.get(i).setAddedBy(surveyExtraInfo.get(i).getUserId());
					answers.get(i).setDuration(surveyExtraInfo.get(i).getDuration());
					answers.get(i).setEntryDate(String.format("%d-%d-%d", surveyExtraInfo.get(i).getDate().getDate(), surveyExtraInfo.get(i).getDate().getDay(), surveyExtraInfo.get(i).getDate().getMonth()));
				});
			
			dbService.getDao(SurveyAnswerDAO.class).addAnswerList(answers);
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
		try {
			/**List<Answer> residentsAllInfo = dbService.getDao(SurveyAnswerDAO.class).getResidents();
			residents = new AnswerConverter().entityListToResidentList(residentsAllInfo);*/
			//return residents;
			List<ResidentDTO> residents = dbService.getDao(SurveyAnswerDAO.class).getResidents();
			residents.forEach(resident -> resident.setImageUrl(ImageUtilService.makeFullImageurl(restUrlProperty, resident.getImageUrl())));
			return residents;
		} catch(JdbiException jex) {
			throw new CustomSqlException("Exception: " +jex.getLocalizedMessage());
		}
	}

	@Override
	public ResidentDetailDTO getAnswerByFilledId(String filledId) {
		try {
			ResidentDetailDTO residentDetail = new ResidentDetailDTO();
			String surveyor = ""; 
			List<QuestionOption> questionOption = dbService.getDao(SurveyAnswerDAO.class).getAllQuestionWithOptions();
			List<Integer> questionWithOptions = questionOption.stream().map(questionOpt -> questionOpt.getQuestionId()).collect(Collectors.toList());
			Answer answer = dbService.getDao(SurveyAnswerDAO.class).getAnswerByFilledId(filledId);
			surveyor = dbService.getDao(UserDAO.class).getUserFullNameById(answer.getAddedBy());
			residentDetail.setSurveyor(surveyor);
			List<FamilyMemberDTO> familyMembers = residentService.getAllFamilyMembersFromFamilyId(filledId);
			residentDetail.setFamilyMembers(familyMembers);
			IntStream.rangeClosed(1, 60).forEach(i -> {
				if(questionWithOptions.contains(i)) {
					try {
						Method getAnswerMethod = Answer.class.getMethod(String.format("getAnswer%d", i));
						String ans = extractAnswer(getAnswerMethod.invoke(answer), questionOption.get(questionWithOptions.indexOf(i)));
						Method setAnswerMethod = answer.getClass().getMethod(String.format("setAnswer%d", i), String.class);
						setAnswerMethod.invoke(answer, ans);
					} catch (NoSuchMethodException e) {
						log.info(String.format("Error : %s", e.getMessage()));
					} catch (SecurityException e) {
						log.info(String.format("Error : %s", e.getMessage()));
					} catch (IllegalAccessException e) {
						log.info(String.format("Error : %s", e.getMessage()));
					} catch (IllegalArgumentException e) {
						log.info(String.format("Error : %s", e.getMessage()));
					} catch (InvocationTargetException e) {
						log.info(String.format("Error : %s", e.getMessage()));
					}
				}
			});
			
			//Setting the district of the resident manually
			answer.setAnswer12(dbService.getDao(DistrictDAO.class).getDistrictNameNepaliByDistrictId(Integer.parseInt(answer.getAnswer12())));
			//Setting the image of the houseowner and the document
			answer.setAnswer47(ImageUtilService.makeFullImageurl(restUrlProperty, answer.getAnswer47()));
			answer.setAnswer48(ImageUtilService.makeFullImageurl(restUrlProperty, answer.getAnswer48()));
			answer.setAnswer49(ImageUtilService.makeFullImageurl(restUrlProperty, answer.getAnswer49()));
			answer.setAnswer50(ImageUtilService.makeFullImageurl(restUrlProperty, answer.getAnswer50()));
			answer.setAnswer52(ImageUtilService.makeFullImageurl(restUrlProperty, answer.getAnswer52()));
			residentDetail.setResidentDetail(answer);
			return residentDetail;
		} catch(JdbiException jex) {
			throw new CustomSqlException("Exception: " +jex.getLocalizedMessage());
		}
	}

	/**
	 * @param rawAnswer String 
	 * @param questionOption 
	 * @return String in Nepali form.
	 */
	private String extractAnswer(Object rawAnswer, QuestionOption questionOption) {
		log.info("#########################");
		String rawanswer = rawAnswer.toString();
		log.info(rawanswer);
		switch(questionOption.getQuestionType()) {
		case CHECKBOX:
			if(rawanswer.contains(",")) {
				return replaceArrayBracket(Stream.of(rawanswer.split(","))
						.map(option -> {
							return questionOption.getOptions().get(Integer.parseInt(option)).getOptionText();
						})
						.collect(Collectors.toList()).toString());
			} else {
				return questionOption.getOptions().get(Integer.parseInt(rawanswer)).getOptionText();
			}
			
			
		case CHECKBOX_N:
			if(rawanswer.contains(",")) {
				String[] extractedData = rawanswer.split(",");
				List<String> answer = Stream.of(extractedData)
					.map(keyValueData -> {
						String[] extractedPair = keyValueData.split(":");
						return questionOption.getOptions().get(Integer.parseInt(extractedPair[0])).getOptionText()
								+ "(" + extractedPair[1] + ")";
					}).collect(Collectors.toList());
				return replaceArrayBracket(answer.toString());
			} else {
				String[] extractedData = rawanswer.split(":");
	 			return questionOption.getOptions().get(Integer.parseInt(extractedData[0])).getOptionText() + "(" + extractedData[1] +")";
			}
			
			
		case RADIO:
				return questionOption.getOptions().get(Integer.parseInt(rawanswer)).getOptionText();
				
		case RADIO_D:
			if(rawanswer.contains(":")) {
				String[] extractedData = rawanswer.split(":");
				return questionOption.getOptions().get(Integer.parseInt(extractedData[0])).getOptionText() + "(" + extractedData[1] + ")";
			}
			
			return questionOption.getOptions().get(Integer.parseInt(rawanswer)).getOptionText();
			
		case RATING_M:
			if(rawanswer.contains(",")) {
				String[] extractedData = rawanswer.split(",");
				List<String> options = new ArrayList<>();
				IntStream.range(0, extractedData.length)
				.forEach(i -> {
					String s = questionOption.getOptions().get(i).getOptionText() + "(" + extractedData[i] +")";
					options.add(s);
				});
				return replaceArrayBracket(options.toString());
			} else {
				return questionOption.getOptions().get(0).getOptionText() + "(" + rawanswer +")";
			}
			
		case DROPDOWN:
			return questionOption.getOptions().get(Integer.parseInt(rawanswer)).getOptionText();
		
		default:
			return "";
		}
	}
	
	private String replaceArrayBracket(String rawString) {
		return rawString.replace("[", "").replace("]", "");
	}
}
