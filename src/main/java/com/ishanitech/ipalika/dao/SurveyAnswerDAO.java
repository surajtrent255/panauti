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

import com.ishanitech.ipalika.model.Answer;
import com.ishanitech.ipalika.model.Option;
import com.ishanitech.ipalika.model.Question;
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
	@SqlQuery("SELECT filled_id as filledId, a_1 AS answer1, a_2 AS answer2, a_3 AS answer3, a_4 AS answer4, a_12 AS answer12, a_47 AS answer_47 FROM answer GROUP BY filled_id")
	@RegisterBeanMapper(Answer.class)
	List<Answer> getResidents();
	
	@UseClasspathSqlLocator
	@SqlQuery("answer_full_detail")
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
