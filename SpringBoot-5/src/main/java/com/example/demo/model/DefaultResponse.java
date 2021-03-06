package com.example.demo.model;

public class DefaultResponse {

	private int status;
	private String message;

	public DefaultResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DefaultResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "DefaultResponse [status=" + status + ", message=" + message + "]";
	}

}
