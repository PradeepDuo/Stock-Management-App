package com.ff.Stock_Management_App.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ff.Stock_Management_App.entity.Product;
import com.ff.Stock_Management_App.repository.ProductRepository;
import com.ff.Stock_Management_App.util.Helper;

@Repository
public class ProductDao {
	@Autowired
	private ProductRepository productRepository;
	
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	} 
	
	public Product deleteProduct(String name) {
		Optional<Product> optional = productRepository.findByName(name);
		Product product = Helper.checkOptional(optional);
			productRepository.delete(product);
			return product;
	}
	
	public Optional<Product> findByName(String name) {
		return productRepository.findByName(name);
	}
	public Optional<List<Product>> findByCategory(String name) {
		return productRepository.findByCategory(name);
	}
	public Optional<List<Product>> findByCompany(String name) {
		return productRepository.findByCompany(name);
	}
	public List<Product> stockCheck() {
		return productRepository.findAll();
	}

}
