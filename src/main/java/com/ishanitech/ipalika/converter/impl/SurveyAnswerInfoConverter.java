package com.ishanitech.ipalika.converter.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ishanitech.ipalika.converter.BaseConverter;
import com.ishanitech.ipalika.dto.SurveyExtraInfoDTO;
import com.ishanitech.ipalika.model.SurveyAnswerInfo;

public class SurveyAnswerInfoConverter extends BaseConverter<SurveyAnswerInfo, SurveyExtraInfoDTO> {

	@Override
	public SurveyAnswerInfo fromDto(SurveyExtraInfoDTO dto) {
		SurveyAnswerInfo surveyAnswer = new SurveyAnswerInfo();
		surveyAnswer.setEntryDate(dto.getDate());
		surveyAnswer.setDuration(dto.getDuration());
		surveyAnswer.setFilledId(dto.getFilledFormId());
		return surveyAnswer;
	}

	@Override
	public SurveyExtraInfoDTO fromEntity(SurveyAnswerInfo entity) {
		SurveyExtraInfoDTO surveyExtraInfo = new SurveyExtraInfoDTO();
		return surveyExtraInfo;
	}

	@Override
	public List<SurveyAnswerInfo> fromDto(List<SurveyExtraInfoDTO> dtos) {
		return dtos.stream().map(this::fromDto).collect(Collectors.toList());
	}

	@Override
	public List<SurveyExtraInfoDTO> fromEntity(List<SurveyAnswerInfo> entities) {
		return entities.stream().map(this::fromEntity).collect(Collectors.toList());
	}
	

}
