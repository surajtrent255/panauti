package com.ishanitech.ipalika.dao;

import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.ishanitech.ipalika.model.Form;

/**
 * {@code FormDAO} dao interface which does all CRUD related to form entity.
 * @author <b> Umesh Bhujel </b>
 * @since 1.0
 */
public interface FormDAO {
	
	/**
	 * Inserts the form into the table `form`
	 * @param form entity
	 * @return void
	 * @author <b> Umesh Bhujel </b>
	 * @since 1.0
	 */
	@SqlUpdate("INSERT INTO form(`form_id`, `form_name`) VALUES (:formId, :formName)")
	public void addForm(@BindBean Form form);
}
