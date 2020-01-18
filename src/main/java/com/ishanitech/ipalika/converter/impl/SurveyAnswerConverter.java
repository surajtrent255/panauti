package com.ishanitech.ipalika.converter.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ishanitech.ipalika.converter.BaseConverter;
import com.ishanitech.ipalika.dto.SurveyAnswerDTO;
import com.ishanitech.ipalika.model.SurveyAnswer;

public class SurveyAnswerConverter extends BaseConverter<SurveyAnswer, SurveyAnswerDTO> {

	@Override
	public List<SurveyAnswer> fromDto(List<SurveyAnswerDTO> dtos) {
		return dtos.stream().map(this::fromDto).collect(Collectors.toList());
	}

	@Override
	public List<SurveyAnswerDTO> fromEntity(List<SurveyAnswer> entities) {
		return entities.stream().map(this::fromEntity).collect(Collectors.toList());
	}

	@Override
	public SurveyAnswer fromDto(SurveyAnswerDTO dto) {
		SurveyAnswer surveyAnswer = new SurveyAnswer();
		surveyAnswer.setQuestionId(dto.getQuestionId());
		surveyAnswer.setAnswerText(dto.getAnswer());
		surveyAnswer.setFilledId(dto.getFilledId());
		return surveyAnswer;
	}

	@Override
	public SurveyAnswerDTO fromEntity(SurveyAnswer entity) {
		SurveyAnswerDTO surveyAnswerDTO = new SurveyAnswerDTO();
		return surveyAnswerDTO;
	}
}
