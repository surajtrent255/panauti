package com.ishanitech.ipalika.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ResidentDTO {
	private int id;
	private String houseOwner;
	private String houseNo;
	private String tole;
	private String phoneNo;
	private String kittaNo;
	private String imageUrl;
	private int totalFamilyMembers;
	private String filledId;
}