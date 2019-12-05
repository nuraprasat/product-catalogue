package com.product.exception;

import com.product.model.ErrorModel;

public class ProductCatalogueException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private ErrorModel e;
	
	public ProductCatalogueException(ErrorModel e) {
		this.e = e;
	}
	
	public ErrorModel getErrorModel() {
		return this.e;
	}

}
