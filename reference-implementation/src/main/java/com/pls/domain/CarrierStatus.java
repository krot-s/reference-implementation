package com.pls.domain;

/**
 * Carrier status.
 * 
 * @author User
 *
 */
public enum CarrierStatus {
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
	private CarrierStatus(String humanReadble) {
		this.humanReadble = humanReadble;
	}
}
