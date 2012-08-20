package com.pls.service;

import java.util.List;

import com.pls.domain.Carrier;

/**
 * Carriers service.
 * @author User
 *
 */
public interface CarrierService {
	/**
	 * Get by id.
	 * @param id 
	 * @return carrier.
	 */
	Carrier getById(Long id);
	
	/**
	 * Get all.
	 * @return all carriers.
	 */
	List<Carrier> getAllCarriers();
	
	/**
	 * Save new.
	 * @param carrier 
	 */
	void addCarrier(Carrier carrier);
}
