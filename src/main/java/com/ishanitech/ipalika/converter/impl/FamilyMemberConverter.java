package com.ishanitech.ipalika.converter.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ishanitech.ipalika.converter.BaseConverter;
import com.ishanitech.ipalika.dto.FamilyMemberDTO;
import com.ishanitech.ipalika.model.FamilyMember;

public class FamilyMemberConverter extends BaseConverter<FamilyMember, FamilyMemberDTO>{

	@Override
	public FamilyMember fromDto(FamilyMemberDTO dto) {
		FamilyMember familyMember = new FamilyMember();
		
		familyMember.setMemberId(dto.getMemberId());
		familyMember.setMainId(dto.getMainId());
		familyMember.setName(dto.getName());
		familyMember.setOccupation(dto.getOccupation());
		familyMember.setRelation(dto.getRelation());
		familyMember.setEducation(dto.getEducation());
		familyMember.setGender(dto.getGender());
		familyMember.setMaritalStatus(Integer.parseInt(dto.getMaritalStatus()));
		familyMember.setVoterCard(Boolean.parseBoolean(dto.getVoterCard()));
		familyMember.setAddress(dto.getAddress());
		familyMember.setHealthCondition(dto.getHealthCondition());
		familyMember.setDateOfBirth(dto.getDateOfBirth());
		familyMember.setIsDead(dto.getIsDead());
		familyMember.setAge(Integer.parseInt(dto.getAge()));
		
		return familyMember;
	}

	@Override
	public FamilyMemberDTO fromEntity(FamilyMember entity) {

		FamilyMemberDTO  familyMemberDTO = new FamilyMemberDTO();
		familyMemberDTO.setMemberId(entity.getMemberId());
		familyMemberDTO.setMainId(entity.getMainId());
		familyMemberDTO.setName(entity.getName());
		familyMemberDTO.setOccupation(entity.getOccupation());
		familyMemberDTO.setRelation(entity.getRelation());
		familyMemberDTO.setEducation(entity.getEducation());
		familyMemberDTO.setGender(entity.getGender());
		familyMemberDTO.setMaritalStatus(String.valueOf(entity.getMaritalStatus()));
		familyMemberDTO.setVoterCard(String.valueOf(entity.getVoterCard()));
		familyMemberDTO.setAddress(entity.getAddress());
		familyMemberDTO.setHealthCondition(entity.getHealthCondition());
		familyMemberDTO.setDateOfBirth(entity.getDateOfBirth());
		familyMemberDTO.setIsDead(entity.getIsDead());
		familyMemberDTO.setAge(String.valueOf(entity.getAge()));;
		
		
		return familyMemberDTO;
	}
	
	@Override
	public List<FamilyMember> fromDto(List<FamilyMemberDTO> dtos) {
		return dtos.stream().map(this::fromDto).collect(Collectors.toList());
	}

	
	@Override
	public List<FamilyMemberDTO> fromEntity(List<FamilyMember> entities) {
		return entities.stream().map(this::fromEntity).collect(Collectors.toList());
	}
	
}
