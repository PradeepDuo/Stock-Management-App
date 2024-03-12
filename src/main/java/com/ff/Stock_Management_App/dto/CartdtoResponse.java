package com.ff.Stock_Management_App.dto;

public class CartdtoResponse {
	
	private String productName;
	private int quantity;
	private String message;
	
	
	public CartdtoResponse(String productName, String message) {
		
		this.productName = productName;
		this.message = message;
	}
	
	
	public CartdtoResponse(String productName, int quantity) {
		super();
		this.productName = productName;
		this.quantity = quantity;
	}
	
	


	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	

}
