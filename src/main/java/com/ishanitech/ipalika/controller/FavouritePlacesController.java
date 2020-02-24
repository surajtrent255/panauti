package com.ishanitech.ipalika.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ishanitech.ipalika.dto.FavouritePlaceDTO;
import com.ishanitech.ipalika.dto.ResponseDTO;
import com.ishanitech.ipalika.exception.CustomSqlException;
import com.ishanitech.ipalika.service.FavouritePlacesService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Tanchhowpa
 *	email: rev.x17@gmail.com
 *	Jan 29, 2020 10:16:40 AM
 */

@Slf4j
@RequestMapping("/favourite-place")
@RestController
public class FavouritePlacesController {
	
	private final FavouritePlacesService favouritePlacesService;

	public FavouritePlacesController(FavouritePlacesService favouritePlacesService) {
		this.favouritePlacesService = favouritePlacesService;
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping
	public ResponseDTO<List<FavouritePlaceDTO>> getFavouritePlaces() {
		return new ResponseDTO<List<FavouritePlaceDTO>> (favouritePlacesService.getFavouritePlaces());
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/image")
	public void uploadImageForFavouritePlace(@RequestParam("picture") MultipartFile image) {
		log.info(String.format("Image name: %s", image.getOriginalFilename()));
		favouritePlacesService.addFavouritePlaceImage(image);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void addFavouritePlace(HttpServletResponse http,
			@RequestBody List<FavouritePlaceDTO> favouritePlaceInfo ) throws CustomSqlException {
		log.info("incomming request");
		favouritePlacesService.addFavouritePlace(favouritePlaceInfo);
	}
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/single")
	public void addSingleFavouritePlace(HttpServletResponse http, @RequestBody FavouritePlaceDTO favouritePlaceInfo) throws CustomSqlException
	{
		favouritePlacesService.addSingleFavouritePlace(favouritePlaceInfo);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping("/detail/{placeId}")
	public ResponseDTO<FavouritePlaceDTO> getFavouritePlaceByPlaceId(@PathVariable("placeId") String placeId) throws CustomSqlException {
		return new ResponseDTO<FavouritePlaceDTO>(favouritePlacesService.getFavouritePlaceByPlaceId(placeId));
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@DeleteMapping("/{placeId}")
	public void deleteFavouritePlaceByPlaceId(@PathVariable("placeId") String placeId) throws CustomSqlException {
		favouritePlacesService.deleteFavouritePlaceByPlaceId(placeId);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping("/{placeId}")
	public void updateFavouritePlaceByPlaceId(HttpServletResponse http,
			@RequestBody FavouritePlaceDTO favouritePlaceInfo, @PathVariable("placeId") String placeId) throws CustomSqlException {
		favouritePlacesService.updateFavouritePlaceByPlaceId(favouritePlaceInfo, placeId);
	}
		
	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping("/types")
	public ResponseDTO<List<String>> getTypesofFavouritePlaces() throws CustomSqlException {
		return new ResponseDTO<List<String>> (favouritePlacesService.getTypesofFavouritePlaces());
	}
	
	
	//This needs to be refined
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("editImage/{placeId}")
	public void deleteFileDemo(HttpServletResponse http,@PathVariable("placeId") String placeId) {
		favouritePlacesService.deleteFileDemo(placeId);
	}

}
