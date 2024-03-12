package com.ff.Stock_Management_App.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ff.Stock_Management_App.dto.CartdtoRequest;
import com.ff.Stock_Management_App.dto.CartdtoResponse;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "Order_Info")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "order_Id")
	@SequenceGenerator(name = "order_Id",allocationSize = 1,initialValue = 101)
	private int id;
	
	@Positive(message = "The Price must always be positive")
	@NotNull
	private double total_price;
	
	@Positive(message = "The Price must always be positive")
	@NotNull
	private double total_price_with_gst;
	
	
	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(name="order_id"),
	inverseJoinColumns = @JoinColumn(name="product_id"),name = "Order_History_Info")
	private List<Product> products;
	
	
	
	
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
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
