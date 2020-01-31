package com.ishanitech.ipalika.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import com.ishanitech.ipalika.model.FamilyMember;

/**
 * 
 * @author Tanchhowpa
 *	email: rev.x17@gmail.com
 *	Jan 30, 2020 1:31:41 PM
 */

public interface ResidentDAO {

	
	@SqlQuery("SELECT member_id FROM family_member")
	List<String> getAllMemberIds();

	
	
	@UseClasspathSqlLocator
	@SqlBatch("insert_family_members")
	void addFamilyMembers(@BindBean List<FamilyMember> familyMembers);



	@SqlQuery("SELECT fm.full_name AS name, "
			+ " fr.relation_nepali AS relation, "
			+ " fm.age AS age, "
			+ " g.gender_english AS gender, "
			+ " fm.marital_status AS maritalStatus, "
			+ " aq.qualification_nep AS education, "
			+ " fm.occupation AS occupation,"
			+ " fm.has_voter_id AS voterCard,"
			+ " fm.migration AS address, "
			+ " fm.health_status AS healthCondition,"
			+ " fm.member_id AS memberId, "
			+ " fm.date_of_birth AS dateOfBirth, "
			+ " fm.is_dead AS isDead "
			+ " FROM family_member fm "
			+ " INNER JOIN family_relation fr "
			+ " ON fm.relation_id = fr.relation_id "
			+ " INNER JOIN academic_qualification aq "
			+ " ON fm.qualification_id = aq.qualification_id "
			+ " INNER JOIN gender g "
			+ " ON fm.gender_id = g.gender_id "
			+ " WHERE fm.family_id = :familyId ")
	@RegisterBeanMapper(FamilyMember.class)
	List<FamilyMember> getAllFamilyMembersFromFamilyId(@Bind("familyId") String familyId);

	
	
	@SqlQuery("SELECT fm.full_name AS name, "
			+ " fr.relation_nepali AS relation, "
			+ " fm.age AS age, "
			+ " g.gender_english AS gender, "
			+ " fm.marital_status AS maritalStatus, "
			+ " aq.qualification_nep AS education, "
			+ " fm.occupation AS occupation,"
			+ " fm.has_voter_id AS voterCard,"
			+ " fm.migration AS address, "
			+ " fm.health_status AS healthCondition,"
			+ " fm.member_id AS memberId, "
			+ " fm.date_of_birth AS dateOfBirth, "
			+ " fm.is_dead AS isDead "
			+ " FROM family_member fm "
			+ " INNER JOIN family_relation fr "
			+ " ON fm.relation_id = fr.relation_id "
			+ " INNER JOIN academic_qualification aq "
			+ " ON fm.qualification_id = aq.qualification_id "
			+ " INNER JOIN gender g "
			+ " ON fm.gender_id = g.gender_id "
			+ " WHERE fm.member_id = :memberId ")
	@RegisterBeanMapper(FamilyMember.class)
	FamilyMember getMemberDetailsFromMemberId(@Bind("memberId") String memberId);
	
	
}
