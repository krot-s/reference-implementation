package com.pls.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Carrier.
 * 
 * @author User
 *
 */
public class Carrier {
	@NotNull
	private Long id = 1L;
	
	@NotNull
	private String name;
	
	@NotNull
	private CarrierStatus status;
	
	@NotNull
	private String scac;
	
	@NotNull
	private Long mcNumber;
	
	@NotNull
	private Date validUntil;
	
	@NotNull
    @Pattern(regexp = "[0-9]{4}-[0-9]{4}-[0-9]{4}")
	private String taxId;
	
	@NotNull
	private String contactName;

	/**
	 * Get id.
	 * @return id
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
	 * Get status. 
	 * @return status.
	 */
	public CarrierStatus getStatus() {
		return status;
	}

	/**
	 * Set status.
	 * @param status 
	 */
	public void setStatus(CarrierStatus status) {
		this.status = status;
	}

	/**
	 * Get scac.
	 * @return scac
	 */
	public String getScac() {
		return scac;
	}

	/**
	 * Set scac.
	 * @param scac 
	 */
	public void setScac(String scac) {
		this.scac = scac;
	}

	/**
	 * Get mc number.
	 * @return mc number.
	 */
	public Long getMcNumber() {
		return mcNumber;
	}

	/**
	 * Set mc number.
	 * @param mcNumber 
	 */
	public void setMcNumber(Long mcNumber) {
		this.mcNumber = mcNumber;
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
	
	@Override
	public String toString() {
		return this.name;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}

		if (!(object instanceof Carrier)) {
			return false;
		}

		Carrier carrier = (Carrier) object;
		return this.id.equals(carrier.id);
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
}
