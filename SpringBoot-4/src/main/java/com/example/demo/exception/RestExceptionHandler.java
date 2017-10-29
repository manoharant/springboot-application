package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
	@ExceptionHandler(TaskException.class)
	public ResponseEntity<ErrorResponse> taskException(TaskException ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorMessage(ex.getErrorMessage());
		error.setErrorCode(HttpStatus.NO_CONTENT.value());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> genericException(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorMessage(ex.getMessage());
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
}
