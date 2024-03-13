package com.ff.Stock_Management_App.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ff.Stock_Management_App.dto.CartdtoRequest;
import com.ff.Stock_Management_App.dto.ResponseStructure;
import com.ff.Stock_Management_App.entity.Orders;
import com.ff.Stock_Management_App.service.OrdersService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/orders")
public class OrdersController {
	
	@Autowired
	private OrdersService ordersService;

	@PostMapping("/")
	@Operation(description = "A new order added to database to Re-stock Product",summary = "Save Order for Re-stock")
	@ApiResponse(description = "Order Added",responseCode = "201") 
	public ResponseEntity<?> saveOrder(@RequestBody List<CartdtoRequest> productList){
		
		return ordersService.saveOrder(productList);
		
	}
	
	@DeleteMapping("/{orderId}")
	@Operation(description = "Delete a Order in database by giving Order ID",summary = "Delete a Order by ID ")
	@ApiResponse(description = "Order Deleted",responseCode = "200")
	public ResponseEntity<ResponseStructure<String>> deleteOrder(@RequestParam int orderId){
		
		return ordersService.deleteOrder(orderId);
	}
	
	@PostMapping("/customer")
	@Operation(description = "A new order added to database for  Customer Billing ",summary = "Save Order as CustomerBill")
	@ApiResponse(description = "Order Added",responseCode = "201") 
	public ResponseEntity<?> saveOrderByCosutomer(@RequestBody List<CartdtoRequest> productList){
		
		return ordersService.saveOrderByCustomer(productList);
		
	}

	
	
}
