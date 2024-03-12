package com.ff.Stock_Management_App.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ff.Stock_Management_App.dao.OrdersDao;
import com.ff.Stock_Management_App.dao.ProductDao;
import com.ff.Stock_Management_App.dto.CartdtoRequest;
import com.ff.Stock_Management_App.dto.CartdtoResponse;
import com.ff.Stock_Management_App.dto.OrdersDto;
import com.ff.Stock_Management_App.dto.ResponseStructure;
import com.ff.Stock_Management_App.entity.Orders;
import com.ff.Stock_Management_App.entity.Product;
import com.ff.Stock_Management_App.exception.NoProductException;

@Service
public class OrdersService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ResponseStructure<List<CartdtoResponse>> responseStructure;

	@Autowired
	private ResponseStructure<OrdersDto> ordersResponseStructure;

	@Autowired
	private ResponseStructure<String> ordersResponse;

	@Autowired
	private ResponseStructure<List<Orders>> ordersListResponse;

	@Autowired
	private OrdersDao ordersDao;

//manager orders for restock
	public ResponseEntity<?> saveOrder(List<CartdtoRequest> productList) {

		List<CartdtoResponse> cartdtoresponse = new ArrayList();
		List<Double> valid_product_price = new ArrayList();
		List<Product> product_list = new ArrayList<Product>();
		Orders orders = new Orders();
		OrdersDto order_display = new OrdersDto();

		for (CartdtoRequest product : productList) {

			Optional<Product> valid_product = productDao.findByName(product.getProductName());

			if (valid_product.isPresent()) {
				product_list.add(valid_product.get());
				valid_product_price.add(valid_product.get().getPrice() * product.getQuantity());

			} else {
				cartdtoresponse.add(new CartdtoResponse(product.getProductName(), "Product not exist in the Database"));
			}
		}

		if (cartdtoresponse.stream().anyMatch(p -> p.getMessage() != null)) {
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseStructure.setData(cartdtoresponse);
			responseStructure.setMessage("Order not Created Successfully");
			return new ResponseEntity<ResponseStructure<List<CartdtoResponse>>>(responseStructure,
					HttpStatus.BAD_REQUEST);
		} else {
			double totalPrice = valid_product_price.stream().mapToDouble(Double::doubleValue).sum();

			orders.setTotal_price(totalPrice);
			orders.setTotal_price_with_gst(totalPrice * 1.18);
			orders.setProducts(product_list);

			Orders saved_order = ordersDao.saveOrder(orders);

			order_display.setId(orders.getId());
			order_display.setTotal_price(totalPrice);
			order_display.setTotal_price_with_gst(totalPrice * 1.18);
			order_display.setOrdered_list(productList);

			ordersResponseStructure.setStatusCode(HttpStatus.CREATED.value());
			ordersResponseStructure.setData(order_display);
			ordersResponseStructure.setMessage("Order Created Successfully");
			return new ResponseEntity<ResponseStructure<OrdersDto>>(ordersResponseStructure, HttpStatus.CREATED);
		}
	}

	public ResponseEntity<ResponseStructure<String>> deleteOrder(int orderId) {

		if (ordersDao.deleteOrder(orderId) != null) {

			ordersResponse.setStatusCode(HttpStatus.OK.value());
			ordersResponse.setData("Order Deleted Successfully");
			ordersResponse.setMessage(HttpStatus.OK.getReasonPhrase());
			return new ResponseEntity<ResponseStructure<String>>(ordersResponse, HttpStatus.OK);

		} else {
			throw new NoProductException("Product Not exist in database");
		}

	}

//this order is for customer billing
	public ResponseEntity<?> saveOrderByCustomer(List<CartdtoRequest> productList) {

		List<CartdtoResponse> cartdtoresponse = new ArrayList();
		List<Double> valid_product_price = new ArrayList();
		List<Product> product_list = new ArrayList<Product>();
		Orders orders = new Orders();
		OrdersDto order_display = new OrdersDto();

		for (CartdtoRequest product : productList) {
//from database
			Optional<Product> valid_product = productDao.findByName(product.getProductName());

			if (valid_product.isPresent()) {
				if (valid_product.get().getQuantity() >= product.getQuantity()) {
					product_list.add(valid_product.get());
					valid_product_price.add(valid_product.get().getPrice() * product.getQuantity());
				}
				if (valid_product.get().getQuantity() <= product.getQuantity()) {
					cartdtoresponse.add(new CartdtoResponse(product.getProductName(), "Stock Limit Reached"));
				}
			}
			if (valid_product.isEmpty()) {
				cartdtoresponse.add(new CartdtoResponse(product.getProductName(), "Product not exist in the Database"));
			}

		}

		if (cartdtoresponse.stream().anyMatch(p -> p.getMessage() != null)) {
			
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseStructure.setData(cartdtoresponse);
			responseStructure.setMessage("Order not Created Successfully");
			return new ResponseEntity<ResponseStructure<List<CartdtoResponse>>>(responseStructure,
					HttpStatus.BAD_REQUEST);

		} else {
			
			for (CartdtoRequest product : productList) {
				Optional<Product> valid_product = productDao.findByName(product.getProductName());

				int updatedQuantity = valid_product.get().getQuantity() - product.getQuantity();
				valid_product.get().setQuantity(updatedQuantity);
				productDao.saveProduct(valid_product.get());
			}

			double totalPrice = valid_product_price.stream().mapToDouble(Double::doubleValue).sum();

			orders.setTotal_price(totalPrice);
			orders.setTotal_price_with_gst(totalPrice * 1.18);
			orders.setProducts(product_list);

			Orders saved_order = ordersDao.saveOrder(orders);

			order_display.setId(orders.getId());
			order_display.setTotal_price(totalPrice);
			order_display.setTotal_price_with_gst(totalPrice * 1.18);
			order_display.setOrdered_list(productList);

			ordersResponseStructure.setStatusCode(HttpStatus.CREATED.value());
			ordersResponseStructure.setData(order_display);
			ordersResponseStructure.setMessage("Order Created Successfully");
			return new ResponseEntity<ResponseStructure<OrdersDto>>(ordersResponseStructure, HttpStatus.CREATED);
			
		}

	}

}
