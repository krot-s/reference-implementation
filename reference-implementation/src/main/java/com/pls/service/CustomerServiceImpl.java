package com.pls.service;

import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;

import com.pls.domain.Customer;
import com.pls.domain.CustomerStatus;

/**
 * Stub.
 * 
 * @author User
 * 
 */
@Stateless
public class CustomerServiceImpl implements CustomerService {
	private final List<Customer> customers;
	
	private static final int MAGIC_NUMBER = 50;

	/**
	 * Dummy comment.
	 */
	public CustomerServiceImpl() {
		customers = new Vector<Customer>(MAGIC_NUMBER);
		for (long i = 0; i < MAGIC_NUMBER; i++) {
			customers.add(createCustomer(i));
		}
	}

	@Override
	public Customer getById(Long id) {
		for (Customer customer : customers) {
			if (customer.getId().equals(id)) {
				return customer;
			}
		}
		return null;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customers;
	}

	/**
	 * Dummy comment.
	 * @param id 
	 * @return new instance.
	 */
	private static Customer createCustomer(Long id) {
		Customer customer = new Customer();
		customer.setId(id);
		customer.setContactName("Vasya Pupkin");
		customer.setName("Pupkin & Sons");
		customer.setStatus(CustomerStatus.ACTIVE);
		customer.setTaxId("0000-1111-2222");
		customer.setValidUntil(Calendar.getInstance().getTime());
		return customer;
	}

	@Override
	public void addCustomer(Customer customer) {
		customers.add(customer);
	}
}
