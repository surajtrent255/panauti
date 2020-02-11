package com.ishanitech.ipalika.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jdbi.v3.sqlobject.config.KeyColumn;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.ValueColumn;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import com.ishanitech.ipalika.dto.QuestionType;
import com.ishanitech.ipalika.exception.CustomSqlException;
import com.ishanitech.ipalika.model.PopulationReport;
import com.ishanitech.ipalika.model.QuestionReport;
import com.ishanitech.ipalika.utils.ReportUtil;

public interface ReportDAO {
	
	@SqlQuery("SELECT q.id, qt.type_name FROM question q INNER JOIN question_type qt ON q.type_id = qt.type_id WHERE q.reportable = TRUE")
	@KeyColumn("id")
	@ValueColumn("type_name")
	Map<Integer, String> getAllAnswer();
	
	@SqlQuery("SELECT <column> FROM answer")
	List<String> getAllAnswerByAnswerId(@Define String column);
	
	@SqlQuery("SELECT count(*) AS total FROM family_member")
	double getTotalPopulation();
	
	@SqlQuery("SELECT g.gender_id, COUNT(id) AS total " + 
			"	FROM family_member fa " + 
			"	INNER JOIN gender g " + 
			"	ON g.gender_id = fa.gender_id " + 
			"	GROUP BY fa.gender_id " + 
			"	ORDER BY g.gender_id ASC;")
	@KeyColumn("gender_id")
	@ValueColumn("total")
	Map<Integer, Double> getPopulationByGenderId();
	
	@SqlUpdate("REPLACE INTO population_report(based_on, option_1, option_2, option_3, option_4, total) " + 
			"	VALUES (\"AgeGroup\", (SELECT COUNT(*) FROM family_member WHERE age < 1), " + 
			"	(SELECT COUNT(*) FROM family_member WHERE age BETWEEN 0 AND 15), " + 
			"	(SELECT COUNT(*) FROM family_member WHERE age BETWEEN 16 AND 59), " + 
			"	(SELECT COUNT(*) FROM family_member WHERE age > 59), " +
			"   :total)")
	void insertAgeGroupReport(@Bind("total") double total);
	
	@SqlQuery("SELECT aq.qualification_id, COUNT(*) AS total FROM family_member fm " + 
			"INNER JOIN academic_qualification aq " + 
			"ON aq.qualification_id = fm.qualification_id GROUP BY aq.qualification_nep ORDER BY aq.qualification_id ASC;")
	@KeyColumn("qualification_id")
	@ValueColumn("total")
	Map<Integer, Double> getPopulationByQualificationId();
	
	@SqlBatch("REPLACE INTO question_report (question_id, option_1, option_2, option_3, option_4, option_5, option_6, option_7, option_8, option_9, option_10, option_11, option_12, option_13, option_14, option_15, total) "
			+ " VALUES (:questionId, :option1, :option2, :option3, :option4, :option5, :option6, :option7, :option8, :option9, :option10, :option11, :option12, :option13, :option14, :option15, :total)")
	void insertQuestionReports(@BindBean List<QuestionReport> answerReports);
	
	/**
	 * @param populationReports
	 */
	@SqlBatch("REPLACE INTO population_report(based_on, option_1, option_2, option_3, option_4, option_5, option_6, option_7, option_8, option_9, option_10, option_11, option_12, option_13, option_14, option_15, total) "
			+ " VALUES "
			+ " (:basedOn, :option1, :option2, :option3, :option4, :option5, :option6, :option7, :option8, :option9, :option10, :option11, :option12, :option13, :option14, :option15, :total)")
	void insertPopulationReports(@BindBean List<PopulationReport> populationReports);
	
	@SqlQuery("SELECT * FROM population_report")
	@RegisterBeanMapper(PopulationReport.class)
	List<PopulationReport> getAllPopulationReports();
	
	@SqlQuery("SELECT * FROM question_report")
	@RegisterBeanMapper(QuestionReport.class)
	List<QuestionReport> getAllQuestionReport();
	
	@Transaction
	default void generateReport() {
		Map<Integer, String> answerAndTypes = getAllAnswer();
		double totalPopulation = getTotalPopulation();
		Map<Integer, Double> populationByGender = getPopulationByGenderId();
		Map<Integer, Double> populationByQualification = getPopulationByQualificationId();
		List<PopulationReport> populationReports = new ArrayList<>();
		insertAgeGroupReport(totalPopulation); //inserts age group report
		PopulationReport genderReport = new PopulationReport();
		genderReport.setBasedOn("Gender");
		genderReport.setOption1(Double.valueOf(0));
		genderReport.setOption2(Double.valueOf(0));
		genderReport.setOption3(Double.valueOf(0));
		genderReport.setOption4(Double.valueOf(0));
		genderReport.setOption5(Double.valueOf(0));
		genderReport.setOption6(Double.valueOf(0));
		genderReport.setOption7(Double.valueOf(0));
		genderReport.setOption8(Double.valueOf(0));
		genderReport.setOption9(Double.valueOf(0));
		genderReport.setOption10(Double.valueOf(0));
		genderReport.setOption11(Double.valueOf(0));
		genderReport.setOption12(Double.valueOf(0));
		genderReport.setOption13(Double.valueOf(0));
		genderReport.setOption14(Double.valueOf(0));
		genderReport.setOption15(Double.valueOf(0));
		genderReport.setTotal(totalPopulation);
		populationByGender.keySet().forEach(key -> {
			try {
				Method method = PopulationReport.class.getMethod(String.format("setOption%d", key), Double.class);
				method.invoke(genderReport, populationByGender.get(key));
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		});
		
		PopulationReport qualificationReport = new PopulationReport();
		qualificationReport.setBasedOn("Qualification");
		qualificationReport.setOption1(Double.valueOf(0));
		qualificationReport.setOption2(Double.valueOf(0));
		qualificationReport.setOption3(Double.valueOf(0));
		qualificationReport.setOption4(Double.valueOf(0));
		qualificationReport.setOption5(Double.valueOf(0));
		qualificationReport.setOption6(Double.valueOf(0));
		qualificationReport.setOption7(Double.valueOf(0));
		qualificationReport.setOption8(Double.valueOf(0));
		qualificationReport.setOption9(Double.valueOf(0));
		qualificationReport.setOption10(Double.valueOf(0));
		qualificationReport.setOption11(Double.valueOf(0));
		qualificationReport.setOption12(Double.valueOf(0));
		qualificationReport.setOption13(Double.valueOf(0));
		qualificationReport.setOption14(Double.valueOf(0));
		qualificationReport.setOption15(Double.valueOf(0));
		qualificationReport.setTotal(totalPopulation);
		populationByQualification.keySet().forEach(key -> {
			try {
				Method method = PopulationReport.class.getMethod(String.format("setOption%d", key), Double.class);
				method.invoke(qualificationReport, populationByQualification.get(key));
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		});
		populationReports.add(genderReport);
		populationReports.add(qualificationReport);
		insertPopulationReports(populationReports);
		
		List<QuestionReport> rawAnswers = answerAndTypes.keySet().stream()
			.map(item -> {
				String table = String.format("answer_%s", item);
				List<String> answer = getAllAnswerByAnswerId(table);
				if(!answer.isEmpty()) {
					String[] list = answer.toString().replace("[", "").replace("]", "").split(",");
					QuestionReport report = ReportUtil.generateReport(item, list, QuestionType.valueOf(answerAndTypes.get(item)));
					return report;
				} 
				throw new CustomSqlException(String.format("No answers found for question no. %s.", item));
			}).collect(Collectors.toList());
		insertQuestionReports(rawAnswers);
	}
	
}