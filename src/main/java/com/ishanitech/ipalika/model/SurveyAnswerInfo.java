package com.ishanitech.ipalika.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SurveyAnswerInfo {
	private Date entryDate;
	private String duration;
	private String filledId;
}
