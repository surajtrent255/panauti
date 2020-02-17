package com.ishanitech.ipalika.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class FamilyMember {
	@JsonIgnore
	private long id;
	private String memberId;
    private String mainId;			//family Id (Form field id)
    private String name;
    private String occupation;
    private String relation;
    private String education;
    private int age;
    private String gender;
    private int maritalStatus;
    private Boolean voterCard;
    private String address;
    private String healthCondition;
    private String dateOfBirth;
    private Boolean isDead;
}
