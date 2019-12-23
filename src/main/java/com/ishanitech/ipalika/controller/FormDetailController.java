/**
 * @author Umesh Bhujel <yoomesbhujel@gmail.com>
 * Since Dec 20, 2019
 */
package com.ishanitech.ipalika.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ishanitech.ipalika.dto.ResponseDTO;
import com.ishanitech.ipalika.exception.EntityNotFoundException;
import com.ishanitech.ipalika.model.FormDetail;
import com.ishanitech.ipalika.service.FormDetailService;

@RestController
@RequestMapping("/form")
public class FormDetailController {
	FormDetailService formDetailService;
	
	public FormDetailController(FormDetailService formDetailService) {
		this.formDetailService = formDetailService;
	}

	@GetMapping("/{formId}")
	public ResponseDTO<List<FormDetail>> getFormDetailsById(@PathVariable("formId") Integer formId) throws EntityNotFoundException {
		return new ResponseDTO<List<FormDetail>>(formDetailService.getFormDetailById(formId));
	}
}
