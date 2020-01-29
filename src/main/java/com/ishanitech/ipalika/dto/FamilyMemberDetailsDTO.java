package com.ishanitech.ipalika.dto;

import lombok.Data;

@Data
public class FamilyMemberDetailsDTO {
	
	private String memberId;
	private String mainId;			//family ID
	private String name;
	private String occupation;
	private String relation;
	private String education;
	private String age;
	private String gender;
	private String maritalStatus;
	private String voterCard;
	private String address;
	private String healthCondition;
	private String dateOfBirth;
	private Boolean submitStatus;
	private Boolean isDead;

}
