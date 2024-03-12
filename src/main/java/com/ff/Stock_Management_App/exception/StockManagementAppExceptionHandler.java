package com.ff.Stock_Management_App.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ff.Stock_Management_App.dto.ResponseStructure;


@ControllerAdvice
public class StockManagementAppExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	ResponseStructure<String> responseStructure;
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ResponseStructure<String>> handleValidationException(
			ValidationException exception){
		responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		responseStructure.setData("product not created successfully");
		responseStructure.setMessage(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoProductException.class)
	public ResponseEntity<ResponseStructure<String>> noProductException(
			NoProductException exception){
		responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		responseStructure.setData("product not Found");
		responseStructure.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.BAD_REQUEST);
	}
}
