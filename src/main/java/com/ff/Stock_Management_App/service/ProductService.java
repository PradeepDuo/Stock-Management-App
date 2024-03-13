package com.ff.Stock_Management_App.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.ff.Stock_Management_App.dao.ProductDao;
import com.ff.Stock_Management_App.dto.Productdto;
import com.ff.Stock_Management_App.dto.ResponseStructure;
import com.ff.Stock_Management_App.entity.Product;
import com.ff.Stock_Management_App.exception.NoProductException;
import com.ff.Stock_Management_App.exception.ValidationException;
import com.ff.Stock_Management_App.util.Helper;

@Service
public class ProductService {
	@Autowired
	private ResponseStructure<Product> responseStructure;
	@Autowired
	private ResponseStructure<String> responseStructure1;
	@Autowired
	private ResponseStructure<List<Product>> listResponseStructure;
	@Autowired
	private ResponseStructure<List<Productdto>> listResponseStructure1;
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
	
	public ResponseEntity<ResponseStructure<Product>> updateProduct (Product product, BindingResult result,String name){
		if(result.hasErrors()) {
			String errors="";
			for (FieldError error : result.getFieldErrors()) {
				errors +=error.getDefaultMessage()+" ,";
			}
			throw new ValidationException(errors);
		}
		Optional<Product> optional = productDao.findByName(name);
		Product product2 = Helper.checkOptional(optional);
		product2.setName(product.getName());
		product2.setCategory(product.getCategory());
		product2.setCompany(product.getCompany());
		product2.setPrice(product.getPrice());
		product2.setQuantity(product.getQuantity());
		product2.setOrders(product.getOrders());
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
	
	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(String name){
		Optional<List<Product>> optional = productDao.findByCategory(name);
		List<Product> optionalList = Helper.checkOptionalList(optional);
		listResponseStructure.setData( optionalList);
		listResponseStructure.setMessage(HttpStatus.OK.getReasonPhrase());
		listResponseStructure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>> (listResponseStructure,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<Product>>> findByCompany(String name){
		Optional<List<Product>> optional = productDao.findByCompany(name);
		List<Product> optionalList = Helper.checkOptionalList(optional);
		listResponseStructure.setData( optionalList);
		listResponseStructure.setMessage(HttpStatus.OK.getReasonPhrase());
		listResponseStructure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>> (listResponseStructure,HttpStatus.OK);
	}
	
	//Here, Productdto class is used from dto package to display only name and quantity
	public ResponseEntity<ResponseStructure<List<Productdto>>> stockCheck(){
		List<Product> stockCheck = productDao.stockCheck();
		List<Productdto> dtoProductList=new ArrayList<Productdto>();
		System.out.println(dtoProductList);
		
		//iterating the Product List to add to Productdto list
		for(Product product : stockCheck) {
			Productdto dto= new Productdto();
			dto.setName(product.getName());
			dto.setQuantity(product.getQuantity());
			dtoProductList.add(dto);
		}
		
		if(stockCheck!=null) {
			listResponseStructure1.setData( dtoProductList);
			listResponseStructure1.setMessage(HttpStatus.OK.getReasonPhrase());
			listResponseStructure1.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Productdto>>> (listResponseStructure1,HttpStatus.OK);
		}
		else {
			throw new NoProductException("No Products Found in the database");
		}
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteProduct(String name){
		productDao.deleteProduct(name);
		responseStructure1.setData("Product Deleted Successfully");
		responseStructure1.setMessage(HttpStatus.OK.getReasonPhrase());
		responseStructure1.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure1,HttpStatus.OK);
	}

}
