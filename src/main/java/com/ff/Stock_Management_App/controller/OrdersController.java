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

@RestController
@RequestMapping("/orders")
public class OrdersController {
	
	@Autowired
	private OrdersService ordersService;

	@PostMapping("/")
	public ResponseEntity<?> saveOrder(@RequestBody List<CartdtoRequest> productList){
		
		return ordersService.saveOrder(productList);
		
	}
	
	@DeleteMapping("/{orderId}")
	public ResponseEntity<ResponseStructure<String>> deleteOrder(@RequestParam int orderId){
		
		return ordersService.deleteOrder(orderId);
	}
	
	@PostMapping("/customer")
	public ResponseEntity<?> saveOrderByCosutomer(@RequestBody List<CartdtoRequest> productList){
		
		return ordersService.saveOrderByCustomer(productList);
		
	}

	
	
}
