package com.example.demo.exception;

public class TaskException extends Exception {
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public TaskException() {
		super();
	}

	public TaskException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
