package com.bijoy.cms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomExceptionHandler {

	public static ResponseEntity<Object> createErrorResponse(HttpStatus status, String message) {

		  return buildResponseEntity(new ApiError(status, message), status);
	}
	private static ResponseEntity<Object> buildResponseEntity(ApiError apiError, HttpStatus status) {
	       return new ResponseEntity<>(apiError, status);
	   }
}
