package com.ff.Stock_Management_App.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ff.Stock_Management_App.dto.Productdto;
import com.ff.Stock_Management_App.dto.ResponseStructure;
import com.ff.Stock_Management_App.entity.Product;
import com.ff.Stock_Management_App.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/save")
	@Operation(description = "A new product added to database",summary = "Save Product")
	@ApiResponse(description = "Product Added",responseCode = "201")
	public ResponseEntity<ResponseStructure<Product>> saveProduct(
			@Valid @RequestBody Product product,BindingResult result){
		return productService.saveProduct(product,result);
	}
	@PutMapping("/update/{name}")
	@Operation(description = "Update a product database ",summary = "Update Product ")
	@ApiResponse(description = "Product Updated",responseCode = "200")
	public ResponseEntity<ResponseStructure<Product>> updateProduct(
			@Valid @RequestBody Product product,@PathVariable String name,BindingResult result){
		return productService.updateProduct(product, result,name);
	}
	
	@GetMapping("/ByName/{name}")
	@Operation(description = "Find a product databaseby giving product name",summary = "Find a Product by name ")
	@ApiResponse(description = "Product details",responseCode = "200")
	public ResponseEntity<ResponseStructure<Product>> findByName(@PathVariable String name){
		return productService.findByName(name);
	}
	
	@GetMapping("/ByCategory/{name}")
	@Operation(description = "Update a product databaseby giving a category name",summary = "Update Product by category ")
	@ApiResponse(description = "Product details",responseCode = "200")
	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(@PathVariable String name){
		return productService.findByCategory(name);
	}
	
	@GetMapping("/ByCompany/{name}")
	@Operation(description = "Update a product databaseby giving company name",summary = "Update Product by company")
	@ApiResponse(description = "Product details",responseCode = "200")
	public ResponseEntity<ResponseStructure<List<Product>>> findByCompany(@PathVariable String name){
		return productService.findByCompany(name);
	}
	
	//this method is used to fetch the availability of stocks 
	@GetMapping()
	@Operation(description = "Fetch available stock and quantity",summary = "Fetch Stock availablity")
	@ApiResponse(description = "Stock availablity",responseCode = "200")
	public  ResponseEntity<ResponseStructure<List<Productdto>>>stockCheck(){
		return productService.stockCheck();
	}
	
	@DeleteMapping("/delete/{name}")
	@Operation(description = "Delete a product databaseby giving product name",summary = "Delete a Product by name ")
	@ApiResponse(description = "Product Deleted",responseCode = "200")
	public ResponseEntity<ResponseStructure<String>> deleteProduct(@PathVariable String name){
		return productService.deleteProduct(name);
	}
	
}
