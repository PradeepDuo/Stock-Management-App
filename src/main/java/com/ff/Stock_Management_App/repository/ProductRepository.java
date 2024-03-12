package com.ff.Stock_Management_App.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ff.Stock_Management_App.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	public Optional<Product> findByName(String name);
}
