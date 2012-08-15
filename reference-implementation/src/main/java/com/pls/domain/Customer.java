package com.pls.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Customer.
 * 
 * @author User
 *
 */
public class Customer {
	@NotNull
	private Long id; 
	
	@NotNull
	private String name;
	
	@NotNull
	@Pattern(regexp = "[0-9]{4}-[0-9]{4}-[0-9]{4}")
	private String taxId;
	
	@NotNull
	private String contactName;
	
	@NotNull
	private Date validUntil;
	
	@NotNull
	private CustomerStatus status;
	
	/**
	 * Get id.
	 * @return id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set id.
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get name.
	 * @return name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name.
	 * @param name 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get tax id.
	 * @return tax id.
	 */
	public String getTaxId() {
		return taxId;
	}

	/**
	 * Set tax id.
	 * @param taxId 
	 */
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	/**
	 * Get contact name.
	 * @return contact name.
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * Set contact name.
	 * @param contactName 
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * Get status.
	 * @return status. 
	 */
	public CustomerStatus getStatus() {
		return status;
	}

	/**
	 * Set status.
	 * @param status 
	 */
	public void setStatus(CustomerStatus status) {
		this.status = status;
	}

	/**
	 * Get valid until date.
	 * @return valid until date.
	 */
	public Date getValidUntil() {
		return validUntil;
	}

	/**
	 * Set valid until date.
	 * @param validUntil 
	 */
	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		
		if (!(object instanceof Customer)) {
			return false;
		}
		
		Customer customer = (Customer) object;
		return this.id.equals(customer.id);
	}
	
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
}
