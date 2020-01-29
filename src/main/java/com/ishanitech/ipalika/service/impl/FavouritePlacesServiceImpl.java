package com.ishanitech.ipalika.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jdbi.v3.core.JdbiException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ishanitech.ipalika.converter.impl.FavouritePlaceConverter;
import com.ishanitech.ipalika.dao.FavouritePlaceDAO;
import com.ishanitech.ipalika.dto.FavouritePlaceDTO;
import com.ishanitech.ipalika.dto.RequestDTO;
import com.ishanitech.ipalika.exception.CustomSqlException;
import com.ishanitech.ipalika.model.FavouritePlace;
import com.ishanitech.ipalika.service.DbService;
import com.ishanitech.ipalika.service.FavouritePlacesService;
import com.ishanitech.ipalika.utils.FileUtilService;


@Service
public class FavouritePlacesServiceImpl implements FavouritePlacesService {

	private final DbService dbService;
	private final FileUtilService fileUtilService;
	
	
	public FavouritePlacesServiceImpl(DbService dbService, FileUtilService fileUtilService) {
		this.dbService = dbService;
		this.fileUtilService = fileUtilService;
	}



	@Override
	public List<FavouritePlaceDTO> getFavouritePlaces() {
		List<FavouritePlaceDTO> favPlaces = new ArrayList<>();
		try {
			List<FavouritePlace> favPlacesInfo = dbService.getDao(FavouritePlaceDAO.class).getFavouritePlaces();
			favPlaces = new FavouritePlaceConverter().fromEntity(favPlacesInfo);
			return favPlaces;
			
		} catch (JdbiException jex) {
			throw new CustomSqlException("Exception: " + jex.getLocalizedMessage());
		}
	}

	@Override
	public void addFavouritePlaceImage(MultipartFile image) {
		fileUtilService.storeFile(image);
	}



	@Override
	public void addFavouritePlace(FavouritePlaceDTO favouritePlaceInfo) {
		FavouritePlaceConverter favPlaceConverter = new FavouritePlaceConverter();
		FavouritePlace favPlace = favPlaceConverter.fromDto(favouritePlaceInfo);
	
		try {
			dbService.getDao(FavouritePlaceDAO.class).addFavouritePlace(favPlace);
		} catch(JdbiException jex) {
			throw new CustomSqlException("Exception " + jex.getMessage());
		}
	
	}

}
