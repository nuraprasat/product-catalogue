package com.product.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.product.exception.ProductCatalogueException;
import com.product.model.Product;


/*
 * Service Layer should be used for Transactional processes
 * 
 * Calls Repository Layers
 * 
 */
@Service
public interface ProductService {
	
	public List<Product> findAll();
	public Product findByProductCode(String productCode);
	public List<Product> findProductsByCategory(String categoryName);
	public List<Product> findProductsByName(String groupBy, String searchTerm) throws ProductCatalogueException;
	public Map<String,Integer> getSellerCount(String sellerName);
	
}
