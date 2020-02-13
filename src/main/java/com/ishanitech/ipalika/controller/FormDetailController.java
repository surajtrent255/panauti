package com.ishanitech.ipalika.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ishanitech.ipalika.dto.ResponseDTO;
import com.ishanitech.ipalika.exception.EntityNotFoundException;
import com.ishanitech.ipalika.model.FormDetail;
import com.ishanitech.ipalika.service.DistrictService;
import com.ishanitech.ipalika.service.FormDetailService;

/**
 * {@code FormDetailController} is a controller class.
 * @author Umesh Bhujel
 * @since 1.0
 */
@RestController
@RequestMapping("/form-detail")
public class FormDetailController {
	FormDetailService formDetailService;
	DistrictService districtService;
	
	public FormDetailController(FormDetailService formDetailService, DistrictService districtService) {
		this.formDetailService = formDetailService;
		this.districtService = districtService;
		
	}

	@GetMapping("/{formId}")
	public ResponseDTO<List<FormDetail>> getFormDetailsById(@PathVariable("formId") Integer formId) throws EntityNotFoundException {
		return new ResponseDTO<List<FormDetail>>(formDetailService.getFormDetailById(formId));
	}
	
	@GetMapping("/districts")
	public ResponseDTO<List<String>> getListofDistricts() throws EntityNotFoundException {
		return new ResponseDTO<List<String>> (districtService.getListofDistricts());
	}
}
