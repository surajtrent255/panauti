package com.ishanitech.ipalika.dao;

import java.util.List;

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
	
	
	
	
	
	
}
