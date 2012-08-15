package com.pls.service;

import java.util.List;

import com.pls.domain.Load;

/**
 * Load service.
 * 
 * @author User
 *
 */
public interface LoadService {
	/**
	 * Get by id.
	 * @param id 
	 * @return load.
	 */
	Load getById(Long id);
	
	/**
	 * Get all.
	 * @return all loads.
	 */
	List<Load> getAllLoads();
	
	/**
	 * Save new.
	 * @param load  
	 */
	void addLoad(Load load);
}
