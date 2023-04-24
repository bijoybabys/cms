package com.bijoy.cms.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bijoy.cms.exception.ApiError;
import com.bijoy.cms.exception.CustomExceptionHandler;

@SpringBootTest
public class CustomExceptionHandlerTest {

	@Test
	void testCreateErrorResponse() {
		ResponseEntity<Object> response = CustomExceptionHandler.createErrorResponse(HttpStatus.BAD_REQUEST,
				"Bad Request");
		assertNotNull(response);
		ApiError apiError = (ApiError) response.getBody();
		assertNotNull(apiError);
		assertEquals("Bad Request", apiError.getMessage());
	}
}
