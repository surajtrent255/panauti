package com.ishanitech.ipalika.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jdbi.v3.core.JdbiException;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ishanitech.ipalika.converter.impl.FavouritePlaceConverter;
import com.ishanitech.ipalika.dao.FavouritePlaceDAO;
import com.ishanitech.ipalika.dto.FavouritePlaceDTO;
import com.ishanitech.ipalika.exception.CustomSqlException;
import com.ishanitech.ipalika.exception.EntityNotFoundException;
import com.ishanitech.ipalika.model.FavouritePlace;
import com.ishanitech.ipalika.service.DbService;
import com.ishanitech.ipalika.service.FavouritePlacesService;
import com.ishanitech.ipalika.utils.FileUtilService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
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
	public void addFavouritePlace(List<FavouritePlaceDTO> favouritePlaceInfo) {
		List<String> filledIdsInDatabase = dbService.getDao(FavouritePlaceDAO.class).getAllFilledIds();
		List<FavouritePlace> favPlaces = new FavouritePlaceConverter().fromDto(favouritePlaceInfo)
				.stream()
				.filter(favPlace -> !filledIdsInDatabase.contains(favPlace.getFavPlaceId()))
				.collect(Collectors.toList());
		
		try {
			dbService.getDao(FavouritePlaceDAO.class).addFavouritePlaceList(favPlaces);
		} catch(JdbiException jex) {
			throw new CustomSqlException("Exception: " + jex.getMessage());
		}
	}



	@Override
	public FavouritePlaceDTO getFavouritePlaceByPlaceId(String placeId) {
		FavouritePlaceDTO favPlace = new FavouritePlaceDTO();
		try {
			FavouritePlace favPlaceInfo = dbService.getDao(FavouritePlaceDAO.class)
					.getFavouritePlaceByPlaceId(placeId);
			favPlace = new FavouritePlaceConverter().fromEntity(favPlaceInfo);
			return favPlace;
		} catch (JdbiException jex) {
			throw new CustomSqlException("Exception :" + jex.getLocalizedMessage());
		}
	}



	@Override
	public void deleteFavouritePlaceByPlaceId(String placeId) {
		try {
			FavouritePlaceDAO favouritePlaceDAO = dbService.getDao(FavouritePlaceDAO.class);
			favouritePlaceDAO.deleteFavouritePlaceByPlaceId(placeId);
		} catch (JdbiException jex) {
			throw new CustomSqlException("Exception : " + jex.getLocalizedMessage());
		}
	}

	@Override
	public void updateFavouritePlaceByPlaceId(FavouritePlaceDTO favouritePlaceInfo, String placeId) {
		
		FavouritePlace favPlace = new FavouritePlaceConverter().fromDto(favouritePlaceInfo);

		try {
			dbService.getDao(FavouritePlaceDAO.class).updateFavouritePlaceByPlaceId(favPlace, placeId);
		} catch(JdbiException jex) {
			throw new CustomSqlException("Exception: " + jex.getMessage());
		}
	}



	@Override
	public void addSingleFavouritePlace(FavouritePlaceDTO favouritePlaceInfo) {
		FavouritePlace favPlace = new FavouritePlaceConverter().fromDto(favouritePlaceInfo);
		
		try {
			dbService.getDao(FavouritePlaceDAO.class).addFavouritePlaceSingle(favPlace);
		} catch (JdbiException jex) {
			throw new CustomSqlException("Exception :" + jex.getLocalizedMessage());
		}
		
	}



	@Override
	public List<String> getTypesofFavouritePlaces() {
		FavouritePlaceDAO favPlaceDAO = dbService.getDao(FavouritePlaceDAO.class);
		try {
			List<String> favPlaceTypeList = favPlaceDAO.getTypesofFavouritePlaces();
			
			if(favPlaceTypeList.size() > 0) {
				return favPlaceTypeList;
			}
		} catch(UnableToExecuteStatementException ex) {
			log.info("#### Error: " + ex.getMessage());
		}
		throw new EntityNotFoundException("No Results!!!");
	}



	@Override
	public void deleteFile(String demoFileName) {
		fileUtilService.deleteFile(demoFileName);
	}

}
