package com.ff.Stock_Management_App.util;

import java.util.Optional;

import com.ff.Stock_Management_App.entity.Product;
import com.ff.Stock_Management_App.exception.NoProductException;

public class Helper<T> {
	
	public static Product checkOptional(Optional<Product> optional) {
		if(optional.isPresent()) {
			Product product=optional.get();
			return product;
		}
		else {
			throw new NoProductException("No Product Found ");
		}
	}

}
