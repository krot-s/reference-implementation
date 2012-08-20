package com.pls.domain;
import java.math.BigDecimal;

/**
 * Load. 
 * 
 * @author User
 *
 */
public class Load {
	private Long id;
	
	private String shipmentNumber;
	
	private Integer piecies;
	
	private Integer weight;
	
	private Customer customer;
	
	private Carrier carrier;
	
	private BigDecimal customerRate;
	
	private BigDecimal carrierRate;

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
	 * Get shipper name.
	 * @return shipper name.
	 */
	public String getShipmentNumber() {
		return shipmentNumber;
	}

	/**
	 * Set shipper name.
	 * @param shipmentNumber 
	 */
	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
	}

	/**
	 * Get pieces.
	 * @return pieces.
	 */
	public Integer getPiecies() {
		return piecies;
	}

	/**
	 * Set pieces.
	 * @param piecies 
	 */
	public void setPiecies(Integer piecies) {
		this.piecies = piecies;
	}

	/**
	 * Get weight.
	 * @return weight.
	 */
	public Integer getWeight() {
		return weight;
	}

	/**
	 * Set weight.
	 * @param weight 
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	/**
	 * Get customer. 
	 * @return customer. 
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Set customer. 
	 * @param customer 
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Get carrier.
	 * @return carrier.
	 */
	public Carrier getCarrier() {
		return carrier;
	}

	/**
	 * Set carrier.
	 * @param carrier 
	 */
	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

	/**
	 * Get rate.
	 * @return rate. 
	 */
	public BigDecimal getCustomerRate() {
		return customerRate;
	}

	/**
	 * Set rate.
	 * @param customerRate 
	 */
	public void setCustomerRate(BigDecimal customerRate) {
		this.customerRate = customerRate;
	}

	/**
	 * Get carrier rate.
	 * @return carrier rate. 
	 */
	public BigDecimal getCarrierRate() {
		return carrierRate;
	}

	/**
	 * Set carrier rate.
	 * @param carrierRate 
	 */
	public void setCarrierRate(BigDecimal carrierRate) {
		this.carrierRate = carrierRate;
	}
}
