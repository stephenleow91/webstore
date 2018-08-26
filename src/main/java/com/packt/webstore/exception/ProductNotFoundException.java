package com.packt.webstore.exception;

public class ProductNotFoundException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String productId;

	public ProductNotFoundException() {
		super();
	}

	public ProductNotFoundException(String productId) {
		super();
		this.productId = productId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

}
