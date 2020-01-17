package com.ishanitech.ipalika.converter.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ishanitech.ipalika.converter.BaseConverter;
import com.ishanitech.ipalika.dto.SurveyAnswerDTO;
import com.ishanitech.ipalika.model.SurveyAnswer;

public class SurveyAnswerConverter extends BaseConverter<SurveyAnswer, SurveyAnswerDTO> {


	public List<SurveyAnswer> fromSurveyAnswerDtos(List<SurveyAnswerDTO> surveyAnswers) {
		return surveyAnswers.stream().map(this::fromSurveyAnswerDto)
				.flatMap(surveyAnswer -> surveyAnswer.stream())
				.collect(Collectors.toList());
	}
	
	private List<SurveyAnswer> fromSurveyAnswerDto(SurveyAnswerDTO surveyAnswerDto) {
		List<SurveyAnswer> surveyAnswerLists = new ArrayList<>();
		//conditional answer based on types...
		SurveyAnswer surveyAnswer = new SurveyAnswer();
		surveyAnswer.setQuestionId(surveyAnswerDto.getQuestionId());
		surveyAnswer.setAnswerText(surveyAnswerDto.getAnswer());
		surveyAnswer.setFilledId(surveyAnswerDto.getFilledId());
		surveyAnswerLists.add(surveyAnswer);
		/*
		 * case CHECKBOX: String[] answers = generateArray(surveyAnswerDto.getAnswer(),
		 * ","); //List<SurveyAnswer> survAns = Stream.of(answers).map(answer -> {
		 * SurveyAnswer ans = new SurveyAnswer(); ans.setAnswerText(null);
		 * ans.setAnswerId(Integer.parseInt(answer));
		 * ans.setQuestionId(surveyAnswerDto.getQuestionId());
		 * ans.setFilledId(surveyAnswerDto.getFilledId()); return ans;
		 * }).collect(Collectors.toList()) .forEach(surveyAnswerLists::add);
		 * 
		 * break;
		 *
		 *}
		 */
		
		return surveyAnswerLists;
	}
	
	@Override
	public SurveyAnswer fromDto(SurveyAnswerDTO dto) {
		SurveyAnswer surveyAnswer = new SurveyAnswer();
		return surveyAnswer;
	}

	@Override
	public SurveyAnswerDTO fromEntity(SurveyAnswer entity) {
		SurveyAnswerDTO surveyAnswerDTO = new SurveyAnswerDTO();
		return surveyAnswerDTO;
	}
	
	private String[] generateArray(String rawValue, String delimiter) {
		return rawValue.split(delimiter);
	}
}
