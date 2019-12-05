package com.product.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.product.exception.ProductCatalogueException;
import com.product.model.ErrorModel;
import com.product.model.Product;


@ControllerAdvice
@RequestMapping(produces = "application/json")
public class RestErrorHandler {
	private static final String INTERNAL_SERVER_ERROR_LOG = "Internal Server Error - ";
	private static final Logger LOGGER = Logger.getLogger(RestErrorHandler.class.getName());
	
	@ExceptionHandler(ProductCatalogueException.class)
	public ResponseEntity<Product> hadleInternalError(final ProductCatalogueException e) {
		LOGGER.log(Level.WARNING,INTERNAL_SERVER_ERROR_LOG+e.getErrorModel());
		return error(e.getErrorModel());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Product> hadleInternalError(final Exception e) {
		LOGGER.log(Level.WARNING,INTERNAL_SERVER_ERROR_LOG+e.getMessage());
		return error(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_LOG);
	}
	
	private ResponseEntity<Product> error(final HttpStatus httpStatus, String message) {
		Product p = getProduct(httpStatus, message);
		return new ResponseEntity<Product>(p,httpStatus);
	}
	
	private ResponseEntity<Product> error(final ErrorModel em) {
		Product p = getProduct(em.getErrorCode(), em.getMessage());
		return new ResponseEntity<Product>(p, em.getErrorCode());
	}
	
	private Product getProduct(HttpStatus httpStatus, String message) {
		ErrorModel em = new ErrorModel(httpStatus, message);
		Product p = new Product();
		p.setErrorModel(em);
		return p;
	}
}
