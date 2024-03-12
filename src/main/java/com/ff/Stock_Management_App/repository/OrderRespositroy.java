package com.ff.Stock_Management_App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ff.Stock_Management_App.entity.Orders;

public interface OrderRespositroy extends JpaRepository<Orders, Integer> {

}
