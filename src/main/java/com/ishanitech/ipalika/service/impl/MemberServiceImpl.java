package com.ishanitech.ipalika.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jdbi.v3.core.JdbiException;
import org.springframework.stereotype.Service;

import com.ishanitech.ipalika.dao.ResidentDAO;
import com.ishanitech.ipalika.dto.FamilyMemberDTO;
import com.ishanitech.ipalika.dto.PaginationTypeClass;
import com.ishanitech.ipalika.dto.RoleWardDTO;
import com.ishanitech.ipalika.exception.CustomSqlException;
import com.ishanitech.ipalika.service.DbService;
import com.ishanitech.ipalika.service.MemberService;
import com.ishanitech.ipalika.utils.CustomQueryCreator;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

	private final DbService dbService;
	
	
	public MemberServiceImpl(DbService dbService) {
		this.dbService = dbService;
	}


	@Override
	public List<FamilyMemberDTO> getMembers(RoleWardDTO roleWardDTO, HttpServletRequest request) {
		try {
			List<FamilyMemberDTO> members;
			
			String caseQuery = CustomQueryCreator.generateQueryWithCase(request, PaginationTypeClass.RESIDENTS);
			if(roleWardDTO.getRole() == 3) {
				members = dbService.getDao(ResidentDAO.class).searchMemberByWard(Integer.toString(roleWardDTO.getWardNumber()), caseQuery);
			} else {
				members = dbService.getDao(ResidentDAO.class).getMembers(caseQuery);
			}
			return members;
		} catch(JdbiException jex) {
			throw new CustomSqlException("Exception: " + jex.getLocalizedMessage());
		}
	}

	
	
}
