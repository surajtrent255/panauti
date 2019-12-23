package com.ishanitech.ipalika.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ishanitech.ipalika.dto.FormDTO;
import com.ishanitech.ipalika.dto.ResponseDTO;
import com.ishanitech.ipalika.exception.CustomSqlException;
import com.ishanitech.ipalika.exception.EntityNotFoundException;
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
	public void postForm(HttpServletResponse response, @RequestBody FormDTO form) throws CustomSqlException {
		Form formModel = new Form();
		formModel.setFormName(form.getFormName());
		formModel.setFormId("yoyoyo");
		response.setHeader("Location", "/form/1");
		formService.addForm(formModel);
	}
	
	/**
	 * Gets the form from database using given id
	 * @param id integer id
	 * @return {@code ResponseDTO<FormDTO>} object
	 * @throws EntityNotFoundException if nothing found with id
	 * @author <b> Umesh Bhujel </b>
	 * @since 1.0
	 */
	@GetMapping("/{id}")
	public ResponseDTO<FormDTO> getFormById(@PathVariable("id") Integer id) throws EntityNotFoundException {
		FormDTO formDTO = new FormDTO();
		Form form = formService.getFormById(id);
		formDTO.setId(form.getId());
		formDTO.setFormId(form.getFormId());
		formDTO.setFormName(form.getFormName());
		return new ResponseDTO<>(formDTO);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{id}")
	public ResponseDTO<FormDTO> updateFormById(@PathVariable("id") Integer id, @RequestBody FormDTO formDTO) {
		Form form = new Form();
		form.setFormName(formDTO.getFormName());
		formService.updateFormById(id, form);
		return new ResponseDTO<>(formDTO);
	}
	
	@PatchMapping("/{id}")
	public ResponseDTO<FormDTO> updatePartiallyById(@PathVariable("id") Integer id, @RequestBody FormDTO formDTO) {
		return null;
	}
	
	/**
	 * Deletes the form record from table based on id
	 * @param id integer id
	 * @throws CustomSqlException if something goes while executing the query.
	 * @return void
	 * @author <b> Umesh Bhujel </b>
	 * @since 1.0
	 */
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteFormById(@PathVariable("id") Integer id) throws CustomSqlException {
		formService.deleteById(id);
	}
}
