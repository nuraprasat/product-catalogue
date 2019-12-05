package com.product.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.exception.ProductCatalogueException;
import com.product.model.Product;
import com.product.model.ShoppingCartMap;
import com.product.service.ProductService;

@RestController
@RequestMapping(value = "/services")
public class RestServicesController {
	
	// services
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ShoppingCartMap shoppingCartMap;
	
	// endpoints
	@PostMapping("/addToCart")
	public void addToCart(
			@RequestParam(value = "productCode") String productCode, 
			@RequestParam(value = "quantity") int quantity
	) {
		shoppingCartMap.addItem(productCode, quantity);
	}
	
	@GetMapping(value = "/getProducts")
	public ResponseEntity<List<Product>> getProducts() {
		return ResponseEntity.ok(productService.findAll());
	}
	
	@GetMapping(value = "/getProductByProductCode/{productCode}")
	public ResponseEntity<Product> getProductByProductCode(@PathVariable String productCode) {
		return ResponseEntity.ok(productService.findByProductCode(productCode));
	}
	
	@GetMapping(value = "/getProductsByCategories/{categoryName}")
	public ResponseEntity<List<Product>> getProductById(@PathVariable String categoryName) {
		return ResponseEntity.ok(productService.findProductsByCategory(categoryName));
	}
	
	@GetMapping(value = "/getProductsByGroup")
	public ResponseEntity<List<Product>> getProductByGroup(@RequestParam String groupBy, @RequestParam String searchTerm) throws ProductCatalogueException {
		return ResponseEntity.ok(productService.findProductsByName(groupBy, searchTerm));
	}
	
	@GetMapping(value = "/getProductBySellerCount/{sellerName}")
	public ResponseEntity<Map<String,Integer>> getCountBySellerName(@PathVariable String sellerName) {
		return ResponseEntity.ok(productService.getSellerCount(sellerName));
	}
	
}