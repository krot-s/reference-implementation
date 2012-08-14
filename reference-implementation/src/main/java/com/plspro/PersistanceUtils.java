package com.plspro;

import javax.persistence.TypedQuery;

/**
 * Various persistant helper methods.
 * 
 * @author User
 * 
 */
public final class PersistanceUtils {
	/**
	 * No instantiation.
	 */
	private PersistanceUtils() {
	}
	
	/**
	 * Mark query as cachable.
	 * 
	 * @param q query to mark.
	 * @param <T> type of the query.
	 * @return same query.
	 */
	public static <T> TypedQuery<T> cacheQuery(TypedQuery<T> q) {
		return q.setHint("org.hibernate.cacheable", true);
	}
}
