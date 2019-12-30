package com.ishanitech.ipalika.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SurveyExtraInfoDTO extends ExtraRequestInfoDTO {
	private Date date;
	private String duration;
}
