package com.ishanitech.ipalika.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ishanitech.ipalika.dto.FamilyMemberDTO;
import com.ishanitech.ipalika.dto.RoleWardDTO;

public interface MemberService {

	List<FamilyMemberDTO> getMembers(RoleWardDTO roleWardDTO, HttpServletRequest request);

}
