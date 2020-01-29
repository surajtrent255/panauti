package com.ishanitech.ipalika.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.ishanitech.ipalika.model.FavouritePlace;

/**
 * 
 * @author Tanchhowpa
 *	email: rev.x17@gmail.com
 *	Jan 29, 2020 11:12:41 AM
 */

public interface FavouritePlaceDAO {

	
	@SqlQuery("SELECT id AS id, fav_place_id AS favPlaceId, fav_place_name AS favPlaceName, fav_place_desc AS favPlaceDesc, fav_place_photo AS favPlacePhoto, fav_place_location AS favPlaceLocation, fav_place_ward AS favPlaceWard FROM favourite_place")
	@RegisterBeanMapper(FavouritePlace.class)
	List<FavouritePlace> getFavouritePlaces();

	
	
	@SqlUpdate("INSERT INTO favourite_place (fav_place_id, "
			+ " fav_place_name, "
			+ " fav_place_desc, "
			+ " fav_place_photo, "
			+ " fav_place_location, "
			+ " fav_place_ward) "
			+ " VALUE (:favPlaceId, "
			+ " :favPlaceName, "
			+ " :favPlaceDesc, "
			+ " :favPlacePhoto, "
			+ " :favPlaceLocation, "
			+ " :favPlaceWard)")
	void addFavouritePlace(@BindBean FavouritePlace favPlace);

}
