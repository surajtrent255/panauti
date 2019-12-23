package com.ishanitech.ipalika.exception;


import java.time.LocalDateTime;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * {@code GlobalExceptionHandler} is a class which handles the exception occured in any controller
 * as the {@code @RestControllerAdvice} annotation is used for this class. If you want to handle 
 * any custom exception then use {@code @ExceptionHandler} annotation along with exception class you
 * want to handle above the method name.
 * @Author Umesh Bhujel
 * @since 1.0
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	/**
	 * This method handle the {@code EntityNotFoundException} exception.
	 * @param ex the custom exception class.
	 * @return {@code ResponseEntity} instance with {@code ApiError} type.
	 * @since 1.0
	 * @Author Umesh Bhujel
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ApiError> handleNotFoundException(EntityNotFoundException ex) {
		ApiError apiError =	new ApiError.Builder(EntityNotFoundException.getStatus().value())
				.withMessage(ex.getMessage())
				.withTime(LocalDateTime.now())
				.withDescription("Maybe wrong input is sent or there are no results to show!")
				.build();
		return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
				
	}
	
	@ExceptionHandler(CustomSqlException.class)
	public ResponseEntity<ApiError> handleCustomSqlException(CustomSqlException cex) {
		ApiError apiError = new ApiError.Builder(cex.getStatus().value())
				.withMessage("Error Occured.")
				.withDescription(cex.getMessage())
				.withTime(LocalDateTime.now())
				.build();
		return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
