package com.ff.Stock_Management_App.dto;

import java.util.List;

public class OrdersDto {
	
	private int id;
	private double total_price;
	private double total_price_with_gst;
	private List<CartdtoRequest> ordered_list;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	public double getTotal_price_with_gst() {
		return total_price_with_gst;
	}
	public void setTotal_price_with_gst(double total_price_with_gst) {
		this.total_price_with_gst = total_price_with_gst;
	}
	public List<CartdtoRequest> getOrdered_list() {
		return ordered_list;
	}
	public void setOrdered_list(List<CartdtoRequest> ordered_list) {
		this.ordered_list = ordered_list;
	}
	
	

}
