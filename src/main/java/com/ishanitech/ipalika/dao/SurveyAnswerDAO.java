package com.ishanitech.ipalika.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import com.ishanitech.ipalika.model.SurveyAnswer;
import com.ishanitech.ipalika.model.SurveyAnswerInfo;

/**
 * {@code SurveyAnswerDAO} does database operations for SurveyAnswers.
 * @author <b> Umesh Bhujel
 * @since 1.0
 */
public interface SurveyAnswerDAO {
	
	@SqlBatch("INSERT INTO survey_answer_info(entry_date, duration, filled_id) VALUES (:entryDate, :duration, :filledId)")
	int addSurveyAnswerInfos(@BindBean List<SurveyAnswerInfo> surveyAnsInfo);
	
	@SqlBatch("INSERT INTO survey_answer(`question_id`, `answer_id`, `answer_text`, `filled_id`) VALUES (:questionId, :answerId, :answerText, :filledId)")
	public void addSurveyAnswers(@BindBean List<SurveyAnswer> surveyAnswers);
	
	@Transaction
	default void insertSurveyAnswer(List<SurveyAnswerInfo> surveyAnswerInfos, List<SurveyAnswer> surveyAnswers) {
		addSurveyAnswerInfos(surveyAnswerInfos);
		addSurveyAnswers(surveyAnswers);
	}
	
}
