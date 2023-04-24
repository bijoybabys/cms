package com.bijoy.cms.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ApiError {

	private String status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private String message;
	private String error;
	

	private ApiError() {
		timestamp = LocalDateTime.now();
	}

	ApiError(HttpStatus status) {
		this();
		this.error = status.getReasonPhrase();
		this.status = String.valueOf(status.value());
		
	}

	ApiError(HttpStatus status, Throwable ex) {
		this();
		this.error = status.getReasonPhrase();

		this.status = String.valueOf(status.value());
		this.message = "Unexpected error";
	}

	ApiError(HttpStatus status, String message, Throwable ex) {
		this();
		this.error = status.getReasonPhrase();
		this.status = String.valueOf(status.value());
		this.message = message;
	}
	
	ApiError(HttpStatus status, String message) {
		this();	
		this.error = status.getReasonPhrase();
		this.status = String.valueOf(status.value());
		this.message = message;
		
	}
}
