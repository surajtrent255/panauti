/**
 * @author Umesh Bhujel <yoomesbhujel@gmail.com>
 * Since Dec 23, 2019
 */
package com.ishanitech.ipalika.exception;


import java.time.LocalDateTime;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ApiError> handleNotFoundException(EntityNotFoundException ex) {
		ApiError apiError =	new ApiError.Builder(EntityNotFoundException.getStatus().value())
				.withMessage(ex.getMessage())
				.withTime(LocalDateTime.now())
				.withDescription("Maybe wrong input is sent or there are no results to show!")
				.build();
		return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
				
	}
	
}
