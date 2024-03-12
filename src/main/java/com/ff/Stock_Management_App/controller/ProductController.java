package com.ff.Stock_Management_App.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ff.Stock_Management_App.dto.ResponseStructure;
import com.ff.Stock_Management_App.entity.Product;
import com.ff.Stock_Management_App.service.ProductService;

import jakarta.validation.Valid;
import lombok.val;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService productService;
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Product>> saveProduct(
			@Valid @RequestBody Product product,BindingResult result){
		return productService.saveProduct(product,result);
	}
}
