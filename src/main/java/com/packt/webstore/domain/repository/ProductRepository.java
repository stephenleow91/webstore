package com.packt.webstore.domain.repository;

import java.util.List;
import java.util.Map;

import com.packt.webstore.domain.Product;

public interface ProductRepository {

	Product getProductById(String productId);

	List<Product> getAllProducts();

	List<Product> getProductsByCategory(String category);

	List<Product> getProductsByFilter(Map<String, List<String>> filterParams);

	Product getProductsByCategoryPriceBrand(String category, Map<String, List<String>> filterParams,
			String brand);

	void updateStock(String productId, long noOfUnits);

	void addProduct(Product product);

}
