package com.app.exception;

public class AppGatewayException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String code;

	public AppGatewayException(String message) {
		super(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
