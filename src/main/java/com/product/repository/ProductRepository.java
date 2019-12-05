package com.product.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product.model.Product;

/*
 * Repository Layer is responsible for retrievel of data
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

	public Product findByProductCode(String productCode);
	public List<Product> findProductsByProductCategory(String category);
	public List<Product> findProductsByBrand(String brand);
	public List<Product> findProductsByColor(String color);
	public List<Product> findProductsByPrice(double price);
	public List<Product> findProductsBySize(int size);
	public List<Product> findAll();
	@Query(value = "select count(*) from product where seller_name = :sellerName", nativeQuery = true)
	public int getSellerCountByName(@Param("sellerName") String sellerName);
	
	  
}
