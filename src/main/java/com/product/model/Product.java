package com.product.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Data;

@Data
@Entity
public class Product {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String productCode;
	private String brand;
	private String productCategory;
	private String sellerName;
	private double price;
	private int size;
	private String color;
	
	private transient ErrorModel errorModel;
}
