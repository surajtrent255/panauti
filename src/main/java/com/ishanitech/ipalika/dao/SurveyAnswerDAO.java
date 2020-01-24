package com.ishanitech.ipalika.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import com.ishanitech.ipalika.model.Answer;
import com.ishanitech.ipalika.model.SurveyAnswer;
import com.ishanitech.ipalika.model.SurveyAnswerInfo;

/**
 * {@code SurveyAnswerDAO} does database operations for SurveyAnswers.
 * @author <b> Umesh Bhujel
 * @since 1.0
 */

public interface SurveyAnswerDAO {
	
	/*
	 * @SqlQuery("SELECT filled_id FROM survey_answer_info") List<String>
	 * getAllFilledIds();
	 */
	
	@SqlQuery("SELECT filled_id FROM answer")
	List<String> getAllFilledIds();
	
	@SqlBatch("INSERT INTO survey_answer_info(entry_date, duration, filled_id) VALUES (:entryDate, :duration, :filledId)")
	int[] addSurveyAnswerInfos(@BindBean List<SurveyAnswerInfo> surveyAnsInfo);
	
	@SqlBatch("INSERT INTO survey_answer(`question_id`, `answer_text`, `filled_id`) VALUES (:questionId, :answerText, :filledId)")
	public void addSurveyAnswers(@BindBean List<SurveyAnswer> surveyAnswers);
	
	/**
	 * Adds answers to answer table.
	 * @param answers
	 */
	@UseClasspathSqlLocator
	@SqlBatch("insert_answers")
	public void addAnswers(@BindBean List<Answer> answers);
	
	@Transaction
	default void insertSurveyAnswer(List<SurveyAnswer> surveyAnswers) {
		//addSurveyAnswerInfos(surveyAnswerInfos);
		addSurveyAnswers(surveyAnswers);
	}
	
	@Transaction
	default void addAnswerList(List<Answer> answers) {
		addAnswers(answers);
	}

	/**
	 * @return
	 */
	@SqlQuery("SELECT a_1 AS answer1, a_2 AS answer2, a_3 AS answer3, a_4 AS answer4, a_12 AS answer12, a_47 AS answer_47 FROM answer GROUP BY filled_id")
	@RegisterBeanMapper(Answer.class)
	List<Answer> getResidents();
	
	@UseClasspathSqlLocator
	@SqlQuery("answer_full_detail")
	@RegisterBeanMapper(Answer.class)
	Answer getAnswerByFilledId(@Bind("filledId") String filledId);
}
