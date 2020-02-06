package com.ishanitech.ipalika.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ishanitech.ipalika.dto.FamilyMemberDTO;
import com.ishanitech.ipalika.dto.ResidentDTO;
import com.ishanitech.ipalika.dto.ResidentDetailDTO;
import com.ishanitech.ipalika.dto.ResponseDTO;
import com.ishanitech.ipalika.exception.CustomSqlException;
import com.ishanitech.ipalika.service.ResidentService;
import com.ishanitech.ipalika.service.SurveyAnswerService;

@RequestMapping("/resident")
@RestController
public class ResidentController {
	private final SurveyAnswerService surveyAnswerService;
	private final ResidentService residentService;
	public ResidentController(SurveyAnswerService surveyAnswerService, ResidentService residentService) {
		this.surveyAnswerService = surveyAnswerService;
		this.residentService = residentService;
	}


	@GetMapping
	public ResponseDTO<List<ResidentDTO>>getResidents() {
		return new ResponseDTO<List<ResidentDTO>>(surveyAnswerService.getResident());
	}
	
	//returns full information of the resident by its filled id.
	@GetMapping("/detail/{filledId}")
	public ResponseDTO<ResidentDetailDTO> getFullInformationOfResident(Model model, @PathVariable("filledId") String filledId) {
		return new ResponseDTO<ResidentDetailDTO>(surveyAnswerService.getAnswerByFilledId(filledId));
	}
	
	
	//Adds the family members to a resident
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/add-resident-members")
	public void addResidentMembers(HttpServletResponse http, @RequestBody List<FamilyMemberDTO> familyMemberInfo ) throws CustomSqlException {
		residentService.addResidentMembers(familyMemberInfo);
	}
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping("/family/{familyId}")
	public ResponseDTO<List<FamilyMemberDTO>> getAllFamilyMembersFromFamilyId(@PathVariable("familyId") String familyId) {
		return new ResponseDTO<List<FamilyMemberDTO>>(residentService.getAllFamilyMembersFromFamilyId(familyId));
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping("/member/{memberId}")
	public ResponseDTO<FamilyMemberDTO> getFamilyMemberByMemberId(@PathVariable("memberId") String memberId) {
		return new ResponseDTO<FamilyMemberDTO> (residentService.getMemberDetailsFromMemberId(memberId));
	}
	
	
}
