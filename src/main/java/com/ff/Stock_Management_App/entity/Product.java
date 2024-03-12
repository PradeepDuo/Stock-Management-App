package com.ff.Stock_Management_App.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
@Entity
@Table(name = "Products_Info")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "prod_id")
	@SequenceGenerator(name = "prod_id",allocationSize = 1,initialValue = 1)
	private int id;
	
	@Size(min = 4, message = "Minimum Four character is needed")
	@Pattern(regexp = "^[A-Za-z0-9\\s]*$",message = "Invalid Name Format")
	@NotNull
	@Column(unique = true )
	private String name;
	
	@Size(min = 4, message = "Minimum Four character is needed")
	@Pattern(regexp = "^[A-Za-z0-9&\\s]*$",message = "Invalid Name Format")
	@NotNull
	private String category;
	
	@Size(min = 4, message = "Minimum Four character is needed")
	@Pattern(regexp = "^[A-Za-z0-9@#$%-&*\\s]*$",message = "Invalid Name Format")
	@NotNull
	private String company;
	
	@PositiveOrZero(message = "No Negatives Allowed")
	private int quantity;
	
	@NotNull
	@Positive(message = "The Price must always be positive")
	private double price;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "products")
	private List<Orders> orders;
	
	public List<Orders> getOrders() {
		return orders;
	}
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
	
	

}
