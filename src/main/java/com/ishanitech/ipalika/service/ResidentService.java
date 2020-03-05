package com.ishanitech.ipalika.service;

import java.util.List;

import com.ishanitech.ipalika.dto.FamilyMemberDTO;
import com.ishanitech.ipalika.dto.MemberFormDetailsDTO;

public interface ResidentService {

	void addResidentMembers(List<FamilyMemberDTO> familyMemberInfo);

	List<FamilyMemberDTO> getAllFamilyMembersFromFamilyId(String familyId);

	FamilyMemberDTO getMemberDetailsFromMemberId(String memberId);

	void deleteResidentByFamilyId(String familyId);

	void addResidentSingle(FamilyMemberDTO familyMemberInfo);

	MemberFormDetailsDTO getMemberFormDetails();

	void editMemberInfo(FamilyMemberDTO familyMemberInfo, String memberId);

	void deleteMemberByMemberId(String memberId);

	void setFamilyMemberDead(String memberId);

}
