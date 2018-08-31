package com.packt.webstore.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.packt.webstore.domain.Customer;

public class CustomerMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

		Customer customer = new Customer();
		customer.setCustomerId(rs.getString("CUSTOMER_ID"));
		customer.setAddress(rs.getString("ADDRESS"));
		customer.setName(rs.getString("NAME"));
		customer.setNoOfOrdersMade(rs.getLong("NO_OF_ORDERS_MADE"));

		return customer;
	}

}