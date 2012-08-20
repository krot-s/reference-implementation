package com.pls.service;

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
public class UserServiceImpl implements UserService {
	private static final int MAX_RESULTS_PER_PAGE = 100;

	@Inject
	private EntityManager em;

	@Override
	public User getById(long id) {
		return em.find(User.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		return em.createQuery("from User")
				.setMaxResults(MAX_RESULTS_PER_PAGE)
				.getResultList();
	}

	@Override
	public void addUser(User user) {
		em.persist(user);
	}

	@Override
	public List<User> search(String userId) {
		return PersistanceUtils.cacheQuery(
				em.createNamedQuery("User.searchByUserId", User.class)
				.setParameter("userId", userId))
				.setMaxResults(MAX_RESULTS_PER_PAGE
			).getResultList();
	}
}
