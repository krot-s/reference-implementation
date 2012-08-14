package com.pls.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.pls.domain.User;
import com.pls.persistance.PersistanceUtils;

/**
 * Test user service.
 * 
 * @author User
 * 
 */
@Stateless
public class UserServiceImpl implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int MAX_RESULTS_PER_PAGE = 10;

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
		).setMaxResults(MAX_RESULTS_PER_PAGE)
		.getResultList();	
	}
}
