package com.ff.Stock_Management_App.dto;

//using the cart data transfer object to generate the bill
public class CartdtoRequest {
	
	private String productName;
	private int quantity;
	
	
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
	
	
	
}
