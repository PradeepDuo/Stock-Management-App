package com.ff.Stock_Management_App.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ff.Stock_Management_App.entity.Product;
import com.ff.Stock_Management_App.repository.ProductRepository;

@Repository
public class ProductDao {
	@Autowired
	private ProductRepository productRepository;
	
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	} 

}
