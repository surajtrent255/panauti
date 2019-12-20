/**
 * @author Umesh Bhujel <yoomesbhujel@gmail.com>
 * Since Dec 20, 2019
 */
package com.ishanitech.ipalika.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface OptionDao {
	
	@SqlQuery("SELECT o.option_text as option FROM option o WHERE o.question_id = :questionID")
	public List<String> getAllOptionByQuestionId(@Bind("questionID") Integer questionId);
}
