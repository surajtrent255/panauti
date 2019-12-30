package com.ishanitech.ipalika.converter.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ishanitech.ipalika.converter.BaseConverter;
import com.ishanitech.ipalika.dto.RequestDTO;
import com.ishanitech.ipalika.dto.SurveyAnswerDTO;
import com.ishanitech.ipalika.dto.SurveyExtraInfoDTO;
import com.ishanitech.ipalika.model.SurveyAnswer;
import com.ishanitech.ipalika.model.SurveyAnswerInfo;

public class SurveyAnswerConverter extends BaseConverter<SurveyAnswerInfo, RequestDTO<List<SurveyAnswerDTO>, SurveyExtraInfoDTO>> {

	@Override
	public SurveyAnswerInfo fromDto(RequestDTO<List<SurveyAnswerDTO>, SurveyExtraInfoDTO> dto) {
		SurveyAnswerInfo surveyAnswerInfo = new SurveyAnswerInfo();
		surveyAnswerInfo.setDuration(dto.getExtraInfo().getDuration());
		surveyAnswerInfo.setEntryDate(dto.getExtraInfo().getDate());
		surveyAnswerInfo.setSurveyAnswers(fromSurveyAnswerDtos(dto.getData()));
		return surveyAnswerInfo;
	}

	@Override
	public RequestDTO<List<SurveyAnswerDTO>, SurveyExtraInfoDTO> fromEntity(SurveyAnswerInfo entity) {
		return null;
	}

	private List<SurveyAnswer> fromSurveyAnswerDtos(List<SurveyAnswerDTO> surveyAnswers) {
		return surveyAnswers.stream().map(this::fromSurveyAnswerDto)
				.flatMap(surveyAnswer -> surveyAnswer.stream())
				.collect(Collectors.toList());
	}
	
	private List<SurveyAnswer> fromSurveyAnswerDto(SurveyAnswerDTO surveyAnswerDto) {
		List<SurveyAnswer> surveyAnswerLists = new ArrayList<>();
		//conditional answer based on types...
		SurveyAnswer surveyAnswer = new SurveyAnswer();
		switch (surveyAnswerDto.getQuestionType()) {
		case TEXT:
		case NUMBER:
		case IMAGE:
		case DATE:
		case RATING:
			surveyAnswer.setAnswerId(null);
			surveyAnswer.setQuestionId(surveyAnswerDto.getQuestionId());
			surveyAnswer.setFilledId(surveyAnswerDto.getFilledId());
			surveyAnswer.setAnswerText(surveyAnswerDto.getAnswer());
			break;
		case CHECKBOX:
			String[] answers = generateArray(surveyAnswerDto.getAnswer(), ",");
			List<SurveyAnswer> survAns = Stream.of(answers).map(answer -> {
				SurveyAnswer ans = new SurveyAnswer();
				ans.setAnswerText(null);
				ans.setAnswerId(Integer.parseInt(answer));
				ans.setQuestionId(surveyAnswerDto.getQuestionId());
				ans.setFilledId(surveyAnswerDto.getFilledId());
				return ans;
			}).collect(Collectors.toList());
			
			survAns.forEach(ans -> {
				surveyAnswerLists.add(ans);
			});
			//surveyAnswerLists.add(survAns);
			break;
		case GPS:
			break;
		case MULTI_TEXT:
			break;
		case RADIO_D:
			break;
		default:
			surveyAnswer.setAnswerId(Integer.parseInt(surveyAnswerDto.getAnswer()));
			surveyAnswer.setAnswerId(null);
			surveyAnswer.setQuestionId(surveyAnswerDto.getQuestionId());
			surveyAnswer.setFilledId(surveyAnswerDto.getFilledId());
			surveyAnswerLists.add(surveyAnswer);
			break;
		}
		
		return surveyAnswerLists;
	}
	
	private String[] generateArray(String rawValue, String delimiter) {
		return rawValue.split(delimiter);
	}
}
