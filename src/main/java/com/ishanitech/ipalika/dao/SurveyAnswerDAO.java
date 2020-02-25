package com.ishanitech.ipalika.dao;

import java.util.List;
import java.util.Map;

import org.jdbi.v3.core.result.LinkedHashMapRowReducer;
import org.jdbi.v3.core.result.RowView;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.UseRowReducer;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import com.ishanitech.ipalika.dto.ResidentDTO;
import com.ishanitech.ipalika.model.Answer;
import com.ishanitech.ipalika.model.Option;
import com.ishanitech.ipalika.model.QuestionOption;

/**
 * {@code SurveyAnswerDAO} does database operations for SurveyAnswers.
 * @author <b> Umesh Bhujel
 * @since 1.0
 */

public interface SurveyAnswerDAO {
	
	@SqlQuery("SELECT filled_id FROM answer")
	List<String> getAllFilledIds();
	/**
	 * Adds answers to answer table.
	 * @param answers
	 */
	@UseClasspathSqlLocator
	@SqlBatch("insert_answers")
	public void addAnswers(@BindBean List<Answer> answers);
	
	@Transaction
	default void addAnswerList(List<Answer> answers) {
		addAnswers(answers);
	}

	/**
	 * @return
	 */
	@SqlQuery("SELECT filled_id as filledId, answer_1 AS houseOwner, answer_2 AS tole, answer_3 AS houseNo, answer_4 AS phoneNo, answer_12 AS kittaNo, answer_52 as imageUrl, "
			+ " (SELECT COUNT(*) FROM family_member fm WHERE fm.family_id = a.filled_id) AS totalFamilyMembers "
			+ " FROM answer a")
	@RegisterBeanMapper(ResidentDTO.class)
	List<ResidentDTO> getResidents();
	
	@SqlQuery("SELECT filled_id as filledId, answer_1 AS houseOwner, answer_2 AS tole, answer_3 AS houseNo, answer_4 AS phoneNo, answer_12 AS kittaNo, answer_52 as imageUrl, " + 
			" (SELECT COUNT(*) FROM family_member fm WHERE fm.family_id = a.filled_id) AS totalFamilyMembers " + 
			" FROM answer a WHERE a.answer_1 LIKE CONCAT('%', :searchKey, '%')")
	@RegisterBeanMapper(ResidentDTO.class)
	List<ResidentDTO> searchResidentByKey(@Bind("searchKey") String searchKey);
	
	@SqlQuery("SELECT * FROM answer WHERE filled_id = :filledId")
	@RegisterBeanMapper(Answer.class)
	Answer getAnswerByFilledId(@Bind("filledId") String filledId);
	
	@SqlQuery(" SELECT q.id as q_question_id, "+
			" qt.type_name as q_question_type,"+
			" o.option_id as o_option_id, " +
			" o.option_text as o_option_text " + 
			"	FROM question_type qt " + 
			"	INNER JOIN question q " + 
			"	ON qt.type_id = q.type_id " + 
			"	INNER JOIN options o " + 
			"	ON o.question_id = q.id ;")
	
	@RegisterBeanMapper(value = QuestionOption.class, prefix = "q")
	@RegisterBeanMapper(value = Option.class, prefix = "o")
	@UseRowReducer(QuestionOptionReducer.class)
	List<QuestionOption> getAllQuestionWithOptions();
	
	class QuestionOptionReducer implements LinkedHashMapRowReducer<Integer, QuestionOption>  {

		@Override
		public void accumulate(Map<Integer, QuestionOption> container, RowView rowView) {
			QuestionOption questionOption = container.computeIfAbsent(rowView.getColumn("q_question_id", Integer.class), id -> {
				return rowView.getRow(QuestionOption.class);
			});
			if(rowView.getColumn("o_option_text", String.class) != null) {
				Option option = rowView.getRow(Option.class);
				questionOption.getOptions().add(option);
			}
		}
		
	}
}
