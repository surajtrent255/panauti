package com.ishanitech.ipalika.service;

import java.util.List;

import com.ishanitech.ipalika.dto.FamilyMemberDTO;

public interface ResidentService {

	void addResidentMembers(List<FamilyMemberDTO> familyMemberInfo);

	List<FamilyMemberDTO> getAllFamilyMembersFromFamilyId(String familyId);

	FamilyMemberDTO getMemberDetailsFromMemberId(String memberId);

	void deleteResidentByFamilyId(String familyId);

	void addResidentSingle(FamilyMemberDTO familyMemberInfo);

	List<String> getListofRelation();

}
