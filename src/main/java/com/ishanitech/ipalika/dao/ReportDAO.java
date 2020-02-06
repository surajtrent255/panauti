package com.ishanitech.ipalika.dao;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jdbi.v3.sqlobject.config.KeyColumn;
import org.jdbi.v3.sqlobject.config.ValueColumn;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import com.ishanitech.ipalika.dto.QuestionType;
import com.ishanitech.ipalika.model.AnswerReport;
import com.ishanitech.ipalika.utils.ReportUtil;

public interface ReportDAO {
	
	@SqlQuery("SELECT q.id, qt.type_name FROM question q INNER JOIN question_type qt ON q.type_id = qt.type_id WHERE q.reportable = TRUE")
	@KeyColumn("id")
	@ValueColumn("type_name")
	Map<Integer, String> getAllAnswer();
	
	@SqlQuery("SELECT <column> FROM answer")
	List<String> getAllAnswerByAnswerId(@Define String column);
	
	@SqlBatch("REPLACE INTO question_report (question_id, opt_1, opt_2, opt_3, opt_4, opt_5, opt_6, opt_7, opt_8, opt_9, opt_10, opt_11, opt_12, opt_13, opt_14, opt_15, total) "
			+ " VALUES (:questionId, :option1, :option2, :option3, :option4, :option5, :option6, :option7, :option8, :option9, :option10, :option11, :option12, :option13, :option14, :option15, :total) "
			+ " WHERE question_id = :questionId")
	void insertQuestionReports(@BindBean List<AnswerReport> answerReports);
	
	@Transaction
	default void generateReport() {
		Map<Integer, String> answerAndTypes = getAllAnswer();
		List<AnswerReport> rawAnswers = answerAndTypes.keySet().stream()
			.map(item -> {
				String table = String.format("a_%s", item);
				List<String > answer = getAllAnswerByAnswerId(table);
				String[] list = answer.toString().replace("[", "").replace("]", "").split(",");
				AnswerReport report = ReportUtil.generateReport(item, list, QuestionType.valueOf(answerAndTypes.get(item)));
				return report;
			}).collect(Collectors.toList());
		insertQuestionReports(rawAnswers);
	}
}
