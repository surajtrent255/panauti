package com.ishanitech.ipalika.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ishanitech.ipalika.dto.FavouritePlaceDTO;

public interface FavouritePlacesService {

	public List<FavouritePlaceDTO> getFavouritePlaces();

	public void addFavouritePlaceImage(MultipartFile image);

	public void addFavouritePlace(List<FavouritePlaceDTO> favouritePlaceInfo);

	public FavouritePlaceDTO getFavouritePlaceByPlaceId(String placeId);

	public void deleteFavouritePlaceByPlaceId(String placeId);

	public void updateFavouritePlaceByPlaceId(FavouritePlaceDTO favouritePlaceInfo, String placeId);

	public void addSingleFavouritePlace(FavouritePlaceDTO favouritePlaceInfo);

	public List<String> getTypesofFavouritePlaces();

	public void deleteFile(String demoFileName);
}
