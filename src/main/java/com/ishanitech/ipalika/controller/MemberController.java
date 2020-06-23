package com.ishanitech.ipalika.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
}
