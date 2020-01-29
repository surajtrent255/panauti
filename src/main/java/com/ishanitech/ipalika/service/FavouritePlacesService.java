package com.ishanitech.ipalika.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ishanitech.ipalika.dto.FavouritePlaceDTO;
import com.ishanitech.ipalika.dto.RequestDTO;

public interface FavouritePlacesService {

	public List<FavouritePlaceDTO> getFavouritePlaces();

	public void addFavouritePlaceImage(MultipartFile image);

	public void addFavouritePlace(FavouritePlaceDTO favouritePlaceInfo);
}
