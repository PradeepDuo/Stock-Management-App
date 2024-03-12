package com.ff.Stock_Management_App.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.ff.Stock_Management_App.dao.ProductDao;
import com.ff.Stock_Management_App.dto.ResponseStructure;
import com.ff.Stock_Management_App.entity.Product;
import com.ff.Stock_Management_App.exception.ValidationException;

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
	

}
