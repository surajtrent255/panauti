package com.ishanitech.ipalika.model;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SurveyAnswerInfo {
	private Integer surveyAnswerExtraId;
	private Date entryDate;
	private String duration;
	private List<SurveyAnswer> surveyAnswers;
}
