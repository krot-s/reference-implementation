package com.plspro.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.plspro.PersistanceUtils;
import com.plspro.domain.User;

/**
 * Test EJB.
 * 
 * @author User
 * 
 */
@Stateless
public class TestEJB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	/**
	 * Save user.
	 * 
	 * @param user 
	 */
	public void save(User user) {
		em.persist(user);
	}

	/**
	 * Search users by userId.
	 * 
	 * @param userId 
	 * 
	 * @return users.
	 */
	public List<User> searchUsers(String userId) {
		return  PersistanceUtils.cacheQuery(
			em.createNamedQuery("User.searchByUserId", User.class)
			.setParameter("userId", userId)
		).getResultList();	
	}
}
