package com.ff.Stock_Management_App.dto;

import org.springframework.stereotype.Component;
//this class is designed to display the availability of stock (from Product table)in terms 
//of their name and quantity only.

@Component
public class Productdto {
	
	private String name;
	private int quantity;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
