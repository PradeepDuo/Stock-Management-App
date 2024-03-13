package com.ff.Stock_Management_App.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ff.Stock_Management_App.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	public Optional<Product> findByName(String name);
	public Optional<List<Product>> findByCategory(String name);
	public Optional<List<Product>> findByCompany(String name);
}
