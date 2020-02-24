/**
 * @author Umesh Bhujel <yoomesbhujel@gmail.com>
 * Since Feb 13, 2020
 */
package com.ishanitech.ipalika.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface WardDAO {

	@SqlQuery("SELECT id from ward")
	List<Integer> getAllWardNumbers();
}
