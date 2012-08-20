package com.pls.domain;

/**
 * Carrier status.
 * 
 * @author User
 *
 */
public enum CustomerStatus {
	ACTIVE("Active"),
	INACTIVE("Inactive");
	
	private String humanReadble;
	
	/**
	 * Get human readable version.
	 * @return human readable version.
	 */
	public String getHumanReadble() {
		return humanReadble;
	}

	/**
	 * Default constructor.
	 * 
	 * @param humanReadble 
	 */
	private CustomerStatus(String humanReadble) {
		this.humanReadble = humanReadble;
	}
}
