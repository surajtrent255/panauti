package com.ishanitech.ipalika.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -5133741561910183291L;
	private static HttpStatus status;
	private String message;
	static {
		status = HttpStatus.NOT_FOUND;
	}
	
	public EntityNotFoundException(String message) {
		this.message = message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public static HttpStatus getStatus() {
		return status;
	}
}
