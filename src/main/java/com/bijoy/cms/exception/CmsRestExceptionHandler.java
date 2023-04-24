package com.bijoy.cms.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CmsRestExceptionHandler extends ResponseEntityExceptionHandler{
	
	Logger log = LoggerFactory.getLogger(CmsRestExceptionHandler.class);


	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<Object> handleException(HttpClientErrorException e) {
	    log.error("HttpClientErrorException: ", e);
	    String error = "HttpClientErrorException";
	    return buildResponseEntity(new ApiError(e.getStatusCode(), error, e), e.getStatusCode());
	}

	@ExceptionHandler(HttpServerErrorException.class)
	public  ResponseEntity<Object> handleException(HttpServerErrorException e) {
	    log.error("HttpServerErrorException: ", e);
	    String error = "HttpServerErrorException";
	    return buildResponseEntity(new ApiError(e.getStatusCode(), error, e), e.getStatusCode());
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception e) {
	    log.error("Unknown Exception", e);
	    String error =  "Internal server Error";
	    return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, error, e), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError, HttpStatus status) {
		return new ResponseEntity<>(apiError, status);
	}
}
