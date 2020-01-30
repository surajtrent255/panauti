package com.ishanitech.ipalika.service.impl;

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
		List<String> filledIdsInDatabase = dbService.getDao(ResidentDAO.class).getAllFilledIds();
		
		
		
		List<FamilyMember> familyMembers = new FamilyMemberConverter().fromDto(familyMemberInfo)
				.stream()
				.filter(famMember -> !filledIdsInDatabase.contains(famMember.getMemberId()))
				.collect(Collectors.toList());
		
		try {
			dbService.getDao(ResidentDAO.class).addFamilyMembers(familyMembers);
		} catch(JdbiException jex) {
			throw new CustomSqlException("Exception: " + jex.getMessage());
		}
		
	}

}
