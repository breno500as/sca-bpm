package com.puc.sca.bpm.exception.handler;

import java.time.LocalDateTime;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.puc.sca.bpm.exception.BpmException;
import com.puc.sca.util.exception.ExceptionResponse;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		final ExceptionResponse exceptionResponse = 
				new ExceptionResponse(
						LocalDateTime.now(),
						request.getDescription(false),
						ex.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(ResourceNotFoundException ex, WebRequest request) {
		final ExceptionResponse exceptionResponse = 
				new ExceptionResponse(
						LocalDateTime.now(),
						request.getDescription(false),
						ex.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(BpmException.class)
	public final ResponseEntity<ExceptionResponse> handleExpectationFailedExceptions(BpmException ex, WebRequest request) {
		final ExceptionResponse exceptionResponse = 
				new ExceptionResponse(
						LocalDateTime.now(),
						request.getDescription(false),
						ex.getMessages());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.EXPECTATION_FAILED);
	}
	

}
