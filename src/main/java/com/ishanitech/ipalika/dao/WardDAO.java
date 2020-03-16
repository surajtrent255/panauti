/**
 * @author Umesh Bhujel <yoomesbhujel@gmail.com>
 * Since Feb 13, 2020
 */
package com.ishanitech.ipalika.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.ishanitech.ipalika.model.Ward;

public interface WardDAO {

	@SqlQuery("SELECT ward_number FROM ward w WHERE w.deleted = 0")
	List<Integer> getAllWardNumbers();
	
	
	@SqlUpdate("INSERT INTO ward ( `ward_number`, "
			+ " `location`, "
			+ " `name`, "
			+ " `ward_description`, "
			+ " `main_person`, "
			+ " `contact_no`) "
			+ " VALUES (:wardNumber, "
			+ " :wardLocation, "
			+ " :wardName, "
			+ " :wardDescription,"
			+ " :mainPerson, "
			+ " :contactNumber)")
	void addWard(@BindBean Ward ward);
	
	@SqlQuery("SELECT w.ward_number AS wardNumber,"
			+ "w.ward_location AS wardLocation, "
			+ "w.name AS wardName, "
			+ "w.ward_description AS wardDescription, "
			+ "w.main_person AS wardPerson, "
			+ "w.contact_no AS contactNumber"
			+ "FROM ward w "
			+ "WHERE w.wardNumber =: wardNo")
	Ward getWardByWardNumber(@Bind("wardNo") int wardNo);
	
	
	@SqlUpdate("UPDATE ward SET "
			+ "ward_number =:wardNumber, "
			+ "ward_location =:wardLocation, "
			+ "name =:wardName, "
			+ "ward_description =:wardDescription, "
			+ "main_person =:mainPerson, "
			+ "contact_no =:contactNumber "
			+ "WHERE ward_number =:wardNo")
	void updateWardInfoByWardNumber(@BindBean Ward ward, @Bind("wardNo") int wardNo);


	@SqlUpdate("UPDATE ward w SET w.deleted = 1 WHERE w.ward_number =:wardNo")
	void deleteWardByWardNumber(@Bind("wardNo") int wardNo);
	
}
