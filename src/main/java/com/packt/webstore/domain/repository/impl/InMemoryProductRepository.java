package com.packt.webstore.domain.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;
import com.packt.webstore.exception.ProductNotFoundException;

@Repository
public class InMemoryProductRepository implements ProductRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Product> getAllProducts() {
		Map<String, Object> params = new HashMap<>();
		List<Product> result = jdbcTemplate.query("SELECT * FROM products", params, new ProductMapper());
		return result;
	}

	@Override
	public void updateStock(String productId, long noOfUnits) {
		String SQL = "UPDATE PRODUCTS SET UNITS_IN_STOCK = :unitsInStock WHERE ID = :id";
		Map<String, Object> params = new HashMap<>();
		params.put("unitsInStock", noOfUnits);
		params.put("id", productId);
		jdbcTemplate.update(SQL, params);
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		Map<String, Object> params = new HashMap<>();
		params.put("category", category);
		List<Product> result = jdbcTemplate.query("SELECT * FROM products WHERE CATEGORY = :category", params,
				new ProductMapper());
		return result;
	}

	@Override
	public List<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		String SQL = "SELECT * FROM products WHERE CATEGORY in ( :categories ) AND MANUFACTURER IN ( :brands )";
		return jdbcTemplate.query(SQL, filterParams, new ProductMapper());
	}

	@Override
	public Product getProductById(String productId) {
		String SQL = "SELECT * FROM products WHERE ID = :productId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productId", productId);

		try {
			return jdbcTemplate.queryForObject(SQL, params, new ProductMapper());

		} catch (DataAccessException e) {
			throw new ProductNotFoundException(productId);
		}
	}

	@Override
	public Product getProductsByCategoryPriceBrand(String category, Map<String, List<String>> filterParams,
			String brand) {

		List<String> categoryList = new ArrayList<>();
		categoryList.add(category);

		List<String> brandList = new ArrayList<>();
		brandList.add(brand);

		filterParams.put("category", categoryList);
		filterParams.put("brand", brandList);

		String SQL = "SELECT * FROM products WHERE CATEGORY = :category AND (UNIT_PRICE >= :low AND UNIT_PRICE <= :high) AND MANUFACTURER = :brand";
		return jdbcTemplate.queryForObject(SQL, filterParams, new ProductMapper());
	}

	@Override
	public void addProduct(Product product) {
		String SQL = "INSERT INTO PRODUCTS (ID, NAME, DESCRIPTION, UNIT_PRICE, MANUFACTURER, CATEGORY, CONDITION, UNITS_IN_STOCK, UNITS_IN_ORDER, DISCONTINUED) "
				+ "VALUES (:id, :name, :desc, :price, :manufacturer, :category, :condition, :inStock, :inOrder, :discontinued)";

		Map<String, Object> params = new HashMap<>();
		params.put("id", product.getProductId());
		params.put("name", product.getName());
		params.put("desc", product.getDescription());
		params.put("price", product.getUnitPrice());
		params.put("manufacturer", product.getManufacturer());
		params.put("category", product.getCategory());
		params.put("condition", product.getCondition());
		params.put("inStock", product.getUnitsInStock());
		params.put("inOrder", product.getUnitsInOrder());
		params.put("discontinued", product.isDiscontinued());

		jdbcTemplate.update(SQL, params);
	}

}
