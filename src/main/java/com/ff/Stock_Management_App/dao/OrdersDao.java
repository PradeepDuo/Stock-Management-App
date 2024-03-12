package com.ff.Stock_Management_App.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ff.Stock_Management_App.entity.Orders;
import com.ff.Stock_Management_App.repository.OrderRespositroy;

@Repository
public class OrdersDao {

	@Autowired
	private OrderRespositroy ordersRepository;

	public String deleteOrder(int orderId) {
		ordersRepository.deleteById(orderId);
		return "Deletion Successful";
	}

	public List<Orders> findAll() {
		return ordersRepository.findAll();
	}

	public Orders saveOrder(Orders orders) {
		return ordersRepository.save(orders);
	}
	
	
}
