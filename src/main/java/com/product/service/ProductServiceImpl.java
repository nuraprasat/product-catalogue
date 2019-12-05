package com.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.product.exception.ProductCatalogueException;
import com.product.model.ErrorModel;
import com.product.model.Product;
import com.product.repository.ProductRepository;


@Component
public class ProductServiceImpl implements ProductService {
	private static final Logger LOGGER = Logger.getLogger(ProductServiceImpl.class.getName());
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product findByProductCode(String productCode) {
		return productRepository.findByProductCode(productCode);
	}
	
	@Override
	public List<Product> findProductsByCategory(String categoryName) {
		return productRepository.findProductsByProductCategory(categoryName);
	}

	@Override
	public List<Product> findProductsByName(String groupBy, String searchTerm) throws ProductCatalogueException {
		List<Product> groupByTerm = null;
		groupBy = groupBy.toLowerCase().trim();
		try {
			switch(groupBy) {
				case "brand" :
					searchTerm = searchTerm.toLowerCase().trim();
					groupByTerm = productRepository.findProductsByBrand(searchTerm);
					break;
				case "price" :
					groupByTerm = productRepository.findProductsByPrice(Double.valueOf(searchTerm));
					break;
				case "size" :
					groupByTerm = productRepository.findProductsBySize(Integer.valueOf(searchTerm));
					break;
				case "color" :
					searchTerm = searchTerm.toLowerCase().trim();
					groupByTerm = productRepository.findProductsByColor(searchTerm);
					break;
				default :
					String msg = "Give valid groupby term, you have given " + groupBy;
					LOGGER.log(Level.INFO, msg);
					ErrorModel em = new ErrorModel(HttpStatus.BAD_REQUEST, msg);
					throw new ProductCatalogueException(em);
			}
		} catch (NumberFormatException e) {
			String errorMsg = "number format exception give valid "+groupBy;
			LOGGER.log(Level.WARNING,errorMsg);
			ErrorModel em = new ErrorModel(HttpStatus.BAD_REQUEST, errorMsg);
			throw new ProductCatalogueException(em);
		} catch(Exception e) {
			ErrorModel em = new ErrorModel(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			throw new ProductCatalogueException(em);
		}
		return groupByTerm;
	}

	@Override
	public Map<String, Integer> getSellerCount(String sellerName) {
		int value = productRepository.getSellerCountByName(sellerName);
		Map<String, Integer> op = new HashMap<>();
		op.put(sellerName, value);
		return op;
	}
	
}
