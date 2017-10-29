package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
	@ExceptionHandler(EmployeeException.class)
	public ResponseEntity<ErrorResponse> employeeExceptionHandler(EmployeeException exception) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
		errorResponse.setErrorMessage(exception.getErrorMessage());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> genericExceptionhandler(Exception exception) {
		ErrorResponse errorRes = new ErrorResponse();
		errorRes.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorRes.setErrorMessage("Request could not be processed");
		return new ResponseEntity<ErrorResponse>(errorRes, HttpStatus.BAD_REQUEST);
	}

}
