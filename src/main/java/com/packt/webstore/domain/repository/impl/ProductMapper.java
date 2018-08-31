package com.packt.webstore.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.packt.webstore.domain.Product;

public class ProductMapper implements RowMapper<Product> {

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

		Product product = new Product();
		product.setProductId(rs.getString("ID"));
		product.setName(rs.getString("NAME"));
		product.setDescription(rs.getString("DESCRIPTION"));
		product.setUnitPrice(rs.getBigDecimal("UNIT_PRICE"));
		product.setManufacturer(rs.getString("MANUFACTURER"));
		product.setCategory(rs.getString("CATEGORY"));
		product.setCondition(rs.getString("CONDITION"));
		product.setUnitsInStock(rs.getLong("UNITS_IN_STOCK"));
		product.setUnitsInOrder(rs.getLong("UNITS_IN_ORDER"));
		product.setDiscontinued(rs.getBoolean("DISCONTINUED"));

		return product;
	}

}
