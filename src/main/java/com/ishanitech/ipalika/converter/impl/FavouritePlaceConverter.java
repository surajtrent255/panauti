package com.ishanitech.ipalika.converter.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.ishanitech.ipalika.config.properties.RestBaseProperty;
import com.ishanitech.ipalika.converter.BaseConverter;
import com.ishanitech.ipalika.dto.FavouritePlaceDTO;
import com.ishanitech.ipalika.model.FavouritePlace;
import com.ishanitech.ipalika.utils.ImageUtilService;

public class FavouritePlaceConverter extends BaseConverter<FavouritePlace, FavouritePlaceDTO> {

	@Autowired
	private RestBaseProperty restBaseProperty;
	
	@Override
	public FavouritePlace fromDto(FavouritePlaceDTO dto) {
		FavouritePlace favPlace = new FavouritePlace();
		
		favPlace.setFavPlaceId(dto.getFilledId());
		favPlace.setFavPlaceName(dto.getPlaceName());
		favPlace.setFavPlaceDesc(dto.getPlaceDescription());
		favPlace.setFavPlacePhoto(dto.getPlaceImage());
		favPlace.setFavPlaceLocation(dto.getPlaceGPS());
		favPlace.setFavPlaceWard(dto.getPlaceWard());
		favPlace.setFavPlaceType(dto.getPlaceType());
		
		return favPlace;
	}

	@Override
	public FavouritePlaceDTO fromEntity(FavouritePlace entity) {
		FavouritePlaceDTO favPlaceDTO = new FavouritePlaceDTO();
		favPlaceDTO.setId(entity.getId());
		favPlaceDTO.setFilledId(entity.getFavPlaceId());
		favPlaceDTO.setPlaceName(entity.getFavPlaceName());
		favPlaceDTO.setPlaceDescription(entity.getFavPlaceDesc());
		favPlaceDTO.setPlaceImage(String.format("%s%s", "http://localhost:8888/resource/", entity.getFavPlacePhoto()));
		favPlaceDTO.setPlaceGPS(entity.getFavPlaceLocation());
		favPlaceDTO.setPlaceWard(entity.getFavPlaceWard());
		favPlaceDTO.setPlaceType(entity.getFavPlaceType());
		return favPlaceDTO;
	}
	
	@Override
	public List<FavouritePlace> fromDto(List<FavouritePlaceDTO> dtos) {
		return dtos.stream().map(this::fromDto).collect(Collectors.toList());
	}

	@Override
	public List<FavouritePlaceDTO> fromEntity(List<FavouritePlace> entities) {
		return entities.stream().map(this::fromEntity).collect(Collectors.toList());
	}
	
}
