package com.ishanitech.ipalika.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.ishanitech.ipalika.model.FamilyMember;

/**
 * 
 * 	@author Tanchhowpa
 *	@email rev.x17@gmail.com
 *	@since 1.0
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
			+ " g.gender_nepali AS gender, "
			+ " ms.marital_status_nep AS maritalStatus, "
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
			+ " INNER JOIN marital_status ms"
			+ " ON fm.marital_status = ms.marital_status_id"
			+ " WHERE fm.family_id = :familyId AND fm.deleted = 0 ")
	@RegisterBeanMapper(FamilyMember.class)
	List<FamilyMember> getAllFamilyMembersFromFamilyId(@Bind("familyId") String familyId);
	
	@SqlQuery("SELECT fm.full_name AS name, "
			+ " fr.relation_nepali AS relation, "
			+ " fm.age AS age, "
			+ " g.gender_nepali AS gender, "
			+ " ms.marital_status_nep AS maritalStatus, "
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
			+ " ON fm.gender_id = g.gender_id"
			+ " INNER JOIN marital_status ms"
			+ " ON fm.marital_status = ms.marital_status_id"
			+ " WHERE fm.member_id = :memberId ")
	@RegisterBeanMapper(FamilyMember.class)
	FamilyMember getMemberDetailsFromMemberId(@Bind("memberId") String memberId);

	@SqlUpdate("UPDATE answer a, family_member fm SET a.deleted = 1, fm.deleted = 1 WHERE a.filled_id = fm.family_id AND fm.family_id =:familyId ")
	void deleteResidentByFamilyId(@Bind("familyId") String familyId);

	@SqlUpdate("INSERT INTO family_member (family_id, full_name, relation_id, age, gender_id, marital_status, qualification_id, occupation, has_voter_id, migration, health_status, member_id, date_of_birth) VALUE(:mainId, :name, :relation, :age, :gender, :maritalStatus, :education, :occupation, :voterCard, :address, :healthCondition, :memberId, :dateOfBirth)")
	void addFamilyMemberSingle(@BindBean FamilyMember familyMembers);


	@SqlQuery("SELECT relation_nepali FROM family_relation")
	List<String> getListofRelation();

	@SqlQuery("SELECT qualification_nep FROM academic_qualification")
	List<String> getListofQualification();

	@SqlQuery("SELECT gender_nepali FROM gender")
	List<String> getListofGender();

	@SqlQuery("SELECT marital_status_nep FROM marital_status")
	List<String> getListofMaritalStatus();

	@SqlUpdate("UPDATE family_member SET"
			+ " full_name =:name, "
			+ " relation_id =:relation, "
			+ " age =:age, "
			+ " gender_id =:gender, "
			+ " marital_status =:maritalStatus, "
			+ " qualification_id =:education, "
			+ " occupation =:occupation, "
			+ " has_voter_id =:voterCard, "
			+ " migration =:address, "
			+ " health_status =:healthCondition, "
			+ " date_of_birth =:dateOfBirth"
			+ " WHERE member_id =:memberId")
	void editFamilyMemberInfo(@BindBean FamilyMember familyMember, @Bind("memberId") String memberId);
	
	
}
