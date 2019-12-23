/**
 * @author Umesh Bhujel <yoomesbhujel@gmail.com>
 * Since Dec 23, 2019
 */
package com.ishanitech.ipalika.service;

import com.ishanitech.ipalika.model.Form;

public interface FormService {
	/**
	 *
	 * @param form object
	 */
	public void addForm(Form form);

	/**
	 * @param id integer value id
	 */
	public Form getFormById(Integer id);

	/**
	 * @param id integer id
	 */
	public void deleteById(Integer id);

	/**
	 * @param id
	 * @param formDTO
	 * @return
	 */
	public void updateFormById(Integer id, Form form);
}
