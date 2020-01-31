package com.ishanitech.ipalika.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jdbi.v3.core.JdbiException;
import org.springframework.stereotype.Service;

import com.ishanitech.ipalika.converter.impl.FamilyMemberConverter;
import com.ishanitech.ipalika.dao.ResidentDAO;
import com.ishanitech.ipalika.dto.FamilyMemberDTO;
import com.ishanitech.ipalika.exception.CustomSqlException;
import com.ishanitech.ipalika.model.FamilyMember;
import com.ishanitech.ipalika.service.DbService;
import com.ishanitech.ipalika.service.ResidentService;

@Service
public class ResidentServiceImpl implements ResidentService {
	
	
	private final DbService dbService;
	

	public ResidentServiceImpl(DbService dbService) {
		this.dbService = dbService;
	}

	@Override
	public void addResidentMembers(List<FamilyMemberDTO> familyMemberInfo) {
		List<String> memberIdsInDatabase = dbService.getDao(ResidentDAO.class).getAllMemberIds();
		List<FamilyMember> familyMembers = new FamilyMemberConverter().fromDto(familyMemberInfo)
				.stream()
				.filter(famMember -> !memberIdsInDatabase.contains(famMember.getMemberId()))
				.collect(Collectors.toList());
		
		try {
			dbService.getDao(ResidentDAO.class).addFamilyMembers(familyMembers);
		} catch(JdbiException jex) {
			throw new CustomSqlException("Exception: " + jex.getMessage());
		}
		
	}

	@Override
	public List<FamilyMemberDTO> getAllFamilyMembersFromFamilyId(String familyId) {
		List<FamilyMemberDTO> familyMembers = new ArrayList<>();
		try {
			List<FamilyMember> familyMemberInfo = dbService.getDao(ResidentDAO.class).getAllFamilyMembersFromFamilyId(familyId);
			familyMembers = new FamilyMemberConverter().fromEntity(familyMemberInfo);
			return familyMembers;
		} catch (JdbiException jex) {
			throw new CustomSqlException("Exception :" + jex.getLocalizedMessage());
		}
	}

	@Override
	public FamilyMemberDTO getMemberDetailsFromMemberId(String memberId) {
		FamilyMemberDTO familyMember = new FamilyMemberDTO();
		try {
			FamilyMember familyMemberInfo = dbService.getDao(ResidentDAO.class).getMemberDetailsFromMemberId(memberId);
			familyMember = new FamilyMemberConverter().fromEntity(familyMemberInfo);
			return familyMember;
		} catch (JdbiException jex) {
			throw new CustomSqlException("Exception :" + jex.getLocalizedMessage());
		}
	}

}
