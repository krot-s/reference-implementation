package com.pls.service;

import java.util.List;

import com.pls.domain.Customer;

/**
 * Customers service.
 * @author User
 *
 */
public interface CustomerService {
	/**
	 * Get by id.
	 * @param id 
	 * @return customer.
	 */
	Customer getById(Long id);
	
	/**
	 * Get all.
	 * @return all customers.
	 */
	List<Customer> getAllCustomers();
	
	/**
	 * Save new.
	 * @param customer 
	 */
	void addCustomer(Customer customer);
	
}
