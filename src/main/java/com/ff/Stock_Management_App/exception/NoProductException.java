package com.ff.Stock_Management_App.exception;

public class NoProductException extends RuntimeException {
	String message="";
	public NoProductException(String message) {
		super();
		this.message = message;
	}
	@Override
	public String getMessage() {
		return message;
	}
	
}
