package com.product.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.product.exception.ProductCatalogueException;
import com.product.model.Product;
import com.product.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {
	
	@InjectMocks
	ProductServiceImpl serviceImpl;
	
	@Mock
	ProductRepository productRepository;
	
	@Test
	public void verifyingfindProductsByNametoReturnList() throws Exception {
		Mockito.when(productRepository.findProductsByBrand("lee")).thenReturn(getListOfProduct());
		List<Product> opList = serviceImpl.findProductsByName("brand", "lee");
		Mockito.verify(productRepository,Mockito.times(1)).findProductsByBrand("lee");
		assertNotNull(opList);
		assertEquals(getListOfProduct(), opList);
	}
	
	@Test
	public void verifyingfindProductsByNameWithColortoReturnList() throws Exception {
		Mockito.when(productRepository.findProductsByColor("green")).thenReturn(getListOfProduct());
		List<Product> opList = serviceImpl.findProductsByName("color", "green");
		Mockito.verify(productRepository,Mockito.times(1)).findProductsByColor("green");
		assertNotNull(opList);
		assertEquals(getListOfProduct(), opList);
	}
	
	@Test
	public void verifyingfindProductsByColorWithSizetoReturnList() throws Exception {
		Mockito.when(productRepository.findProductsBySize(38)).thenReturn(getListOfProduct());
		List<Product> opList = serviceImpl.findProductsByName("size", "38");
		Mockito.verify(productRepository,Mockito.times(1)).findProductsBySize(38);
		assertNotNull(opList);
		assertEquals(getListOfProduct(), opList);
	}
	
	@Test
	public void verifyingfindProductsByPriceWithPricetoReturnList() throws Exception {
		Mockito.when(productRepository.findProductsByPrice(100.00)).thenReturn(getListOfProduct());
		List<Product> opList = serviceImpl.findProductsByName("price", "100.00");
		Mockito.verify(productRepository,Mockito.times(1)).findProductsByPrice(100.00);
		assertNotNull(opList);
		assertEquals(getListOfProduct(), opList);
	}
	
	@Test(expected = ProductCatalogueException.class)
	public void verifyingfindProductsByPriceWithDefault() throws Exception {
		serviceImpl.findProductsByName("abcd", "100.00");
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = ProductCatalogueException.class)
	public void verifyingfindProductsByPriceWithException() throws Exception {
		Mockito.when(productRepository.findProductsByPrice(100.00)).thenThrow(NumberFormatException.class);
		serviceImpl.findProductsByName("price", "100.00");
	}
	
	private List<Product> getListOfProduct() {
		List<Product> productList = new ArrayList<>();
		Product product = new Product();
		product.setBrand("lee");
		product.setColor("green");
		product.setId(1);
		product.setPrice(100.00);
		product.setProductCategory("shirt");
		product.setProductCode("RX100");
		product.setSellerName("vijaya");
		product.setSize(38);
		productList.add(product);
		return productList;
	}

}
