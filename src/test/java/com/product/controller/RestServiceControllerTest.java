package com.product.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.model.Product;
import com.product.service.ProductService;

@RunWith(MockitoJUnitRunner.class)
public class RestServiceControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private ProductService productService;
	
	@InjectMocks
	RestServicesController restServiceController;
	
	@Before
	public void init() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(restServiceController).build();
	}

	@Test
	public void AllProductShouldBeReturnedFromService() throws Exception {
		Mockito.when(productService.findAll()).thenReturn(getListOfProduct());
		MvcResult result = this.mockMvc.perform(get("/services/getProducts").accept(MediaType.APPLICATION_JSON_VALUE))
														.andExpect(status().is(200))
														.andReturn();
		ObjectMapper mapper = new ObjectMapper();
		List<Product> proList = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Product>>() {});
		assertNotNull(proList.get(0));
		assertEquals(getListOfProduct().get(0), proList.get(0));
	}
	
	@Test
	public void getProductByProductCodeShouldBeReturnedFromService() throws Exception {
		Mockito.when(productService.findByProductCode(Mockito.anyString())).thenReturn(getListOfProduct().get(0));
		MvcResult result = this.mockMvc.perform(get("/services/getProductByProductCode/{productCode}","RX100").accept(MediaType.APPLICATION_JSON_VALUE))
														.andExpect(status().is(200))
														.andReturn();
		ObjectMapper mapper = new ObjectMapper();
		Product proList = mapper.readValue(result.getResponse().getContentAsString(), Product.class);
		assertNotNull(proList);
		assertEquals(getListOfProduct().get(0), proList);
	}
	
	@Test
	public void getProductByIdShouldBeReturnedFromService() throws Exception {
		Mockito.when(productService.findProductsByCategory(Mockito.anyString())).thenReturn(getListOfProduct());
		MvcResult result = this.mockMvc.perform(get("/services/getProductsByCategories/{categoryName}","shirt").accept(MediaType.APPLICATION_JSON_VALUE))
														.andExpect(status().is(200))
														.andReturn();
		ObjectMapper mapper = new ObjectMapper();
		List<Product> proList = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Product>>() {});
		assertNotNull(proList.get(0));
		assertEquals(getListOfProduct().get(0), proList.get(0));
	}
	
	@Test
	public void getProductsByGroupShouldBeReturnedFromService() throws Exception {
		Mockito.when(productService.findProductsByName(Mockito.anyString(), Mockito.anyString())).thenReturn(getListOfProduct());
		MvcResult result = this.mockMvc.perform(get("/services/getProductsByGroup").param("groupBy", "color").param("searchTerm", "green").accept(MediaType.APPLICATION_JSON_VALUE))
														.andExpect(status().is(200))
														.andReturn();
		ObjectMapper mapper = new ObjectMapper();
		List<Product> proList = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Product>>() {});
		assertNotNull(proList.get(0));
		assertEquals(getListOfProduct().get(0), proList.get(0));
	}
	
	@Test
	public void getProductBySellerCountShouldBeReturnedFromService() throws Exception {
		Map<String, Integer> hMap = new HashMap<>();
		hMap.put("vijaya", 1);
		Mockito.when(productService.getSellerCount(Mockito.anyString())).thenReturn(hMap);
		MvcResult result = this.mockMvc.perform(get("/services/getProductBySellerCount/{sellerName}","vijaya").accept(MediaType.APPLICATION_JSON_VALUE))
														.andExpect(status().is(200))
														.andReturn();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Integer> proList = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<Map<String, Integer>>() {});
		assertNotNull(proList);
		assertTrue(proList.get("vijaya") == 1);
	}

	private List<Product> getListOfProduct() {
		List<Product> productList = new ArrayList<>();
		Product product = new Product();
		product.setBrand("UCB");
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
