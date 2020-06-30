package com.ishanitech.ipalika.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ishanitech.ipalika.dto.FamilyMemberDTO;
import com.ishanitech.ipalika.dto.ResponseDTO;
import com.ishanitech.ipalika.dto.RoleWardDTO;
import com.ishanitech.ipalika.service.MemberService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequestMapping("/member")
@RestController
public class MemberController {

	private final MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@PostMapping
	public ResponseDTO<List<FamilyMemberDTO>> getMembers(@RequestBody RoleWardDTO roleWardDTO, HttpServletRequest request) {
		return new ResponseDTO<List<FamilyMemberDTO>>(memberService.getMembers(roleWardDTO, request));
	}
	
	@PostMapping("/search")
	public ResponseDTO<List<FamilyMemberDTO>> searchMember(HttpServletRequest request, @RequestParam("searchKey") String searchKey, @RequestParam("wardNo") String wardNo) {
		String extractedSearchKey = null;
		try {
			extractedSearchKey = URLDecoder.decode(searchKey, "Utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new ResponseDTO<List<FamilyMemberDTO>> (memberService.searchMember(request, extractedSearchKey, wardNo));
	}
	
	@PostMapping("/search/ward")
	public ResponseDTO<List<FamilyMemberDTO>> searchMemberByWard(HttpServletRequest request, @RequestParam("wardNo") String wardNo) {
		return new ResponseDTO<List<FamilyMemberDTO>> (memberService.searchMemberByWard(request, wardNo));
	}
	
	@PostMapping("/pageLimit")
	public ResponseDTO<List<FamilyMemberDTO>> getMemberByPageLimit(HttpServletRequest request, @RequestParam("wardNo") String wardNo) {
		return new ResponseDTO<List<FamilyMemberDTO>> (memberService.getMemberByPageLimit(request));
	}
	
	@PostMapping("/nextLot")
	public ResponseDTO<List<FamilyMemberDTO>> getNextLotMember(HttpServletRequest request, @RequestBody RoleWardDTO roleWardDTO) {
		return new ResponseDTO<List<FamilyMemberDTO>> (memberService.getNextLotMember(request, roleWardDTO));
	}
	
	
	@PostMapping("/sortBy")
	public ResponseDTO<List<FamilyMemberDTO>> getSortedMember(HttpServletRequest request) {
		return new ResponseDTO<List<FamilyMemberDTO>> (memberService.getSortedMember(request));
	}
}
