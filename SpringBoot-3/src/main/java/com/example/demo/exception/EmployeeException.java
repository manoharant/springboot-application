package com.example.demo.exception;

public class EmployeeException extends Exception {

	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public EmployeeException() {
		super();
	}

	public EmployeeException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
