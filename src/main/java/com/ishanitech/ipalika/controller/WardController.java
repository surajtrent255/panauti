/**
 * @author Umesh Bhujel <yoomesbhujel@gmail.com>
 * Since Feb 18, 2020
 */
package com.ishanitech.ipalika.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ishanitech.ipalika.dto.ResponseDTO;
import com.ishanitech.ipalika.servicer.WardService;

@RestController
@RequestMapping("/ward")
public class WardController {
	private final WardService wardService;
	
	public WardController(WardService wardService) {
		this.wardService = wardService;
	}

	@GetMapping
	public ResponseDTO<List<Integer>> getAllWardsNumbers() {
		List<Integer> wards = wardService.getAllWardNumbers();
		return new ResponseDTO<List<Integer>>(wards);
	}
}
