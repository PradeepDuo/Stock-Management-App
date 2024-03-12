package com.ff.Stock_Management_App.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.server.ResponseStatusException;

import com.ff.Stock_Management_App.dao.ProductDao;
import com.ff.Stock_Management_App.dto.ResponseStructure;
import com.ff.Stock_Management_App.entity.Product;
import com.ff.Stock_Management_App.exception.ValidationException;
import com.ff.Stock_Management_App.util.Helper;

@Service
public class ProductService {
	@Autowired
	private ResponseStructure<Product> responseStructure;
	@Autowired
	private ProductDao productDao;
	
	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product, BindingResult result){
		if(result.hasErrors()) {
			String errors="";
			for (FieldError error : result.getFieldErrors()) {
				errors +=error.getDefaultMessage()+" ,";
			}
			throw new ValidationException(errors);
		}
		responseStructure.setData(productDao.saveProduct(product));
		responseStructure.setMessage(HttpStatus.CREATED.getReasonPhrase());
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Product>>(responseStructure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Product>> updateProduct (Product product, BindingResult result){
		if(result.hasErrors()) {
			String errors="";
			for (FieldError error : result.getFieldErrors()) {
				errors +=error.getDefaultMessage()+" ,";
			}
			throw new ValidationException(errors);
		}
		Optional<Product> optional = productDao.findByName(product.getName());
		Product product2 = Helper.checkOptional(optional);
		responseStructure.setData(productDao.saveProduct(product2));
		responseStructure.setMessage(HttpStatus.OK.getReasonPhrase());
		responseStructure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Product>>(responseStructure,HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Product>> findByName(String name){
		Optional<Product> optional = productDao.findByName(name);
		Product product = Helper.checkOptional(optional);
		responseStructure.setData(product);
		responseStructure.setMessage(HttpStatus.OK.getReasonPhrase());
		responseStructure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Product>> (responseStructure,HttpStatus.OK);
	}
	

}
