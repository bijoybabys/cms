package com.bijoy.cms.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.bijoy.cms.exception.ApiError;
import com.bijoy.cms.exception.CmsRestExceptionHandler;

@SpringBootTest
public class CmsRestExceptionHandlerTest {
	
	@Autowired
	CmsRestExceptionHandler restExceptionHandler;
	
	@Test
	void testHandleException() {
		ResponseEntity<Object> response = restExceptionHandler.handleException(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
		assertNotNull(response);
		ApiError apiError = (ApiError) response.getBody();
		assertNotNull(apiError);
		assertEquals("HttpClientErrorException", apiError.getMessage());
		
		response = restExceptionHandler.handleException(new HttpServerErrorException(HttpStatus.BAD_GATEWAY));
		assertNotNull(response);
	    apiError = (ApiError) response.getBody();
		assertNotNull(apiError);
		assertEquals("HttpServerErrorException", apiError.getMessage());
		
		response = restExceptionHandler.handleException(new Exception());
		assertNotNull(response);
		apiError = (ApiError) response.getBody();
		assertNotNull(apiError);
		assertEquals("Internal server Error", apiError.getMessage());
	}
	
	@Test
	void testApiError() {
		ApiError error = new ApiError(HttpStatus.GATEWAY_TIMEOUT);
		assertNotNull(error.getStatus());
		
		error = new ApiError(HttpStatus.BAD_GATEWAY, new Exception());
		assertNotNull(error.getMessage());
	}

}
