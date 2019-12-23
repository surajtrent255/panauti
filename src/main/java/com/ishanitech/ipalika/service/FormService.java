/**
 * @author Umesh Bhujel <yoomesbhujel@gmail.com>
 * Since Dec 23, 2019
 */
package com.ishanitech.ipalika.service;

import com.ishanitech.ipalika.model.Form;

public interface FormService {
	/**
	 * 
	 * @param form
	 */
	public void addForm(Form form);

	/**
	 * @param id integer value id
	 */
	public Form getFormById(Integer id);
}
