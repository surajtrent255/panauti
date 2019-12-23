package com.ishanitech.ipalika.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ishanitech.ipalika.dto.FormDTO;
import com.ishanitech.ipalika.exception.CustomSqlException;
import com.ishanitech.ipalika.model.Form;
import com.ishanitech.ipalika.service.FormService;

/**
 * {@code FormController} is a controller class responsible for creating, editing and deleting 
 * form related informations.
 * @author <b>Umesh Bhujel </b>
 * @since 1.0
 */
@RequestMapping("/form")
@RestController
public class FormController {
	private FormService formService;
	public FormController(FormService formService) {
		this.formService = formService;
	}

	/**
	 * Handles the post request coming to {@code FormController} on its root url {@link /form}.
	 * @param form dto object
	 * @return void 
	 * @author <b> Umesh Bhujel </b>
	 * @since 1.0
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void postForm(@RequestBody FormDTO form) throws CustomSqlException {
		Form formModel = new Form();
		formModel.setFormName(form.getFormName());
		formModel.setFormId("xyzabc123");
		formService.addForm(formModel);
	}
}
