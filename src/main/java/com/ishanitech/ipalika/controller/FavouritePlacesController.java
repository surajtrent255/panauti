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
import com.ishanitech.ipalika.exception.EntityNotFoundException;
import com.ishanitech.ipalika.exception.FileStorageException;
import com.ishanitech.ipalika.service.FavouritePlacesService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Tanchhowpa email: rev.x17@gmail.com Jan 29, 2020 10:16:40 AM
 */

@Slf4j
@RequestMapping("/favourite-place")
@RestController
public class FavouritePlacesController {

	private final FavouritePlacesService favouritePlacesService;

	public FavouritePlacesController(FavouritePlacesService favouritePlacesService) {
		this.favouritePlacesService = favouritePlacesService;
	}

	
	/**
	 * Gets all the list of Favourite Places
	 * @return {@code ResponseDTO<List<FavouritePlaceDTO>>} object
	 * @author <b> Tanchhowpa Singzango </b>
	 * @since 1.0
	 */
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public ResponseDTO<List<FavouritePlaceDTO>> getFavouritePlaces() {
		return new ResponseDTO<List<FavouritePlaceDTO>>(favouritePlacesService.getFavouritePlaces());
	}
	
	
	/**
	 * This endpoint expects the list of request object ({@code List<FavouritePlaceDTO>}) which has the same format as
	 * {@code FavouritePlace} model.
	 * @param http HttpServletResponse object 
	 * @param favouritePlaceInfo request object
	 * @throws CustomSqlException
	 * @author <b> Tanchhowpa Singzango </b>
	 * @since 1.0
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void addFavouritePlace(HttpServletResponse http, @RequestBody List<FavouritePlaceDTO> favouritePlaceInfo)
			throws CustomSqlException {
		log.info("incomming request");
		favouritePlacesService.addFavouritePlace(favouritePlaceInfo);
	}
	
	
	/**
	 * Deletes the favourite place record from table based on placeId
	 * @param placeId String placeId
	 * @throws CustomSqlException if something goes while executing the query.
	 * @return void
	 * @author <b> Tanchhowpa Singzango </b>
	 * @since 1.0
	 */
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/{placeId}")
	public void deleteFavouritePlaceByPlaceId(@PathVariable("placeId") String placeId) throws CustomSqlException {
		favouritePlacesService.deleteFavouritePlaceByPlaceId(placeId);
	}
	
	
	/**
	 * Updates the favourite place details on basis of placeId with single request object ({@code FavouritePlaceDTO}) which has the same format as
	 * {@code FavouritePlace} model.
	 * @param http HttpServletResponse object 
	 * @param favouritePlaceInfo request object
	 * @param placeId String placeId
	 * @throws CustomSqlException
	 * @author <b> Tanchhowpa Singzango </b>
	 * @since 1.0
	 */
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{placeId}")
	public void updateFavouritePlaceByPlaceId(HttpServletResponse http,
			@RequestBody FavouritePlaceDTO favouritePlaceInfo, @PathVariable("placeId") String placeId)
			throws CustomSqlException {
		favouritePlacesService.updateFavouritePlaceByPlaceId(favouritePlaceInfo, placeId);
	}
	
	
	/**
	 * Gets the favourite place from database using given placeId
	 * @param placeId String placeId
	 * @return {@code ResponseDTO<FavouritePlaceDTO>} object
	 * @throws EntityNotFoundException if nothing found with id
	 * @author <b> Tanchhowpa Singzango </b>
	 * @since 1.0
	 */
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{placeId}")
	public ResponseDTO<FavouritePlaceDTO> getFavouritePlaceByPlaceId(@PathVariable("placeId") String placeId)
			throws EntityNotFoundException {
		return new ResponseDTO<FavouritePlaceDTO>(favouritePlacesService.getFavouritePlaceByPlaceId(placeId));
	}

	
	/**
	 * Uploads the image for favourite place using MultipartFile
	 * @param picture MultipartFile image
	 * @return void
	 * @throws FileStorageException if there is error on storing file
	 * @author <b> Tanchhowpa Singzango </b>
	 * @since 1.0
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/image")
	public void uploadImageFavouritePlace(@RequestParam("picture") MultipartFile image) throws FileStorageException {
		favouritePlacesService.addFavouritePlaceImage(image);
	}

	
	/**
	 * Uploads the edited image for favourite place using MultipartFile
	 * @param picture MultipartFile image
	 * @return void
	 * @throws FileStorageException if there is error on storing file
	 * @author <b> Tanchhowpa Singzango </b>
	 * @since 1.0
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/editImage")
	public void uploadEditedImageFavouritePlace(@RequestParam("picture") MultipartFile image) throws FileStorageException {
		favouritePlacesService.updateFavouritePlaceImage(image);
	}

	
	/**
	 * Deletes the image file for a favourite place using placeId
	 * @param placeId String placeId
	 * @return void
	 * @throws CustomSqlException
	 * @author <b> Tanchhowpa Singzango </b>
	 * @since 1.0
	 */
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/image/{placeId}")
	public void deleteFileDemo(HttpServletResponse http, @PathVariable("placeId") String placeId) throws CustomSqlException {
		favouritePlacesService.deleteFile(placeId);
	}

	
	/**
	 * This endpoint expects a single request object ({@code FavouritePlaceDTO}) which has the same format as
	 * {@code FavouritePlace} model.
	 * @param http HttpServletResponse object 
	 * @param favouritePlaceInfo request object
	 * @throws CustomSqlException
	 * @author <b> Tanchhowpa Singzango </b>
	 * @since 1.0
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/single")
	public void addSingleFavouritePlace(HttpServletResponse http, @RequestBody FavouritePlaceDTO favouritePlaceInfo)
			throws CustomSqlException {
		favouritePlacesService.addSingleFavouritePlace(favouritePlaceInfo);
	}

	
	/**
	 * Gets the list of types of favourite places
	 * @return {@code ResponseDTO<List<String>>} object
	 * @throws CustomSqlException
	 * @author <b> Tanchhowpa Singzango </b>
	 * @since 1.0
	 */
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/type")
	public ResponseDTO<List<String>> getTypesofFavouritePlaces() throws CustomSqlException {
		return new ResponseDTO<List<String>>(favouritePlacesService.getTypesofFavouritePlaces());
	}

}
