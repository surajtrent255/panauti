package com.ishanitech.ipalika.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface DistrictDAO {

	@SqlQuery("SELECT district_name_nep FROM districts")
	public List<String> getListofDistricts();
}
