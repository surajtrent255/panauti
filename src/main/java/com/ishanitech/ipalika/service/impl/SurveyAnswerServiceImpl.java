package com.ishanitech.ipalika.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
import com.ishanitech.ipalika.dto.SurveyAnswerExtraInfoDTO;
import com.ishanitech.ipalika.exception.CustomSqlException;
import com.ishanitech.ipalika.exception.FileStorageException;
import com.ishanitech.ipalika.model.Answer;
import com.ishanitech.ipalika.model.QuestionOption;
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
	public void addSurveyAnswers(RequestDTO<List<SurveyAnswerDTO>, SurveyAnswerExtraInfoDTO> surveyAnswerInfo) {
		SurveyAnswerConverter surveyAnswerConverter = new SurveyAnswerConverter();
		List<String> filledIdsInDatabase = dbService.getDao(SurveyAnswerDAO.class).getAllFilledIds();
		List<SurveyAnswer> surveyAnswers = surveyAnswerConverter
				.fromDto(surveyAnswerInfo.getData());
		try {
			List<Answer> answers = surveyAnswerConverter.fromSuveyAnswersToAnswersList(surveyAnswers)
					.stream().map(surveyAns -> {
						Answer ans = new Answer();
						ans.setAddedBy(surveyAnswerInfo.getInfoData().getUserId());
						ans.setDuration(surveyAnswerInfo.getInfoData().getDuration());
						ans.setEntryDate(surveyAnswerInfo.getInfoData().getDate().toString());
						return ans;
					}).collect(Collectors.toList());
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
			List<QuestionOption> questionOption = dbService.getDao(SurveyAnswerDAO.class).getAllQuestionWithOptions();
			List<Integer> questionWithOptions = questionOption.stream().map(questionOpt -> questionOpt.getQuestionId()).collect(Collectors.toList());
			Answer answer = dbService.getDao(SurveyAnswerDAO.class).getAnswerByFilledId(filledId);
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
			answer.setAnswer47("http://localhost:8888/resource/" + answer.getAnswer47());
			return answer;
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
		String rawanswer = rawAnswer.toString();
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
			
		default:
			return "";
		}
	}
	
	private String replaceArrayBracket(String rawString) {
		return rawString.replace("[", "").replace("]", "");
	}

}
