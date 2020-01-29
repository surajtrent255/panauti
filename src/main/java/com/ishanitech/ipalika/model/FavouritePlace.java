package com.ishanitech.ipalika.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Data
public class FavouritePlace {
	@JsonIgnore
	private long id;
	private long favPlaceId;
	private String favPlaceName;
	private String favPlaceDesc;
	private String favPlacePhoto;
	private String favPlaceLocation;
	private String favPlaceWard;
	
}
