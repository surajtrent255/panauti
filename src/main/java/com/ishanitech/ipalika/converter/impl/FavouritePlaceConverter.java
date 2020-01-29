package com.ishanitech.ipalika.converter.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ishanitech.ipalika.converter.BaseConverter;
import com.ishanitech.ipalika.dto.FavouritePlaceDTO;
import com.ishanitech.ipalika.model.FavouritePlace;

public class FavouritePlaceConverter extends BaseConverter<FavouritePlace, FavouritePlaceDTO> {

	@Override
	public FavouritePlace fromDto(FavouritePlaceDTO dto) {
		FavouritePlace favPlace = new FavouritePlace();
		
		favPlace.setFavPlaceId(dto.getFavPlaceId());
		favPlace.setFavPlaceName(dto.getFavPlaceName());
		favPlace.setFavPlaceDesc(dto.getFavPlaceDesc());
		favPlace.setFavPlacePhoto(dto.getFavPlacePhoto());
		favPlace.setFavPlaceLocation(dto.getFavPlaceLocation());
		favPlace.setFavPlaceWard(dto.getFavPlaceWard());
		return favPlace;
	}

	@Override
	public FavouritePlaceDTO fromEntity(FavouritePlace entity) {
		FavouritePlaceDTO favPlaceDTO = new FavouritePlaceDTO();
		favPlaceDTO.setFavPlaceId(entity.getFavPlaceId());
		favPlaceDTO.setFavPlaceName(entity.getFavPlaceName());
		favPlaceDTO.setFavPlaceDesc(entity.getFavPlaceDesc());
		favPlaceDTO.setFavPlacePhoto(String.format("%s%s", "http://103.233.58.121:8888/resource", entity.getFavPlacePhoto()));
		favPlaceDTO.setFavPlaceLocation(entity.getFavPlaceLocation());
		favPlaceDTO.setFavPlaceWard(entity.getFavPlaceWard());
		return favPlaceDTO;
	}
	
	@Override
	public List<FavouritePlace> fromDto(List<FavouritePlaceDTO> dtos) {;
		return dtos.stream().map(this::fromDto).collect(Collectors.toList());
	}

	@Override
	public List<FavouritePlaceDTO> fromEntity(List<FavouritePlace> entities) {
		return entities.stream().map(this::fromEntity).collect(Collectors.toList());
	}
	
}
