package com.plspro.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.plspro.domain.User;

/**
 * Test EJB.
 * 
 * @author User
 * 
 */
@SessionScoped
public class TestEJB implements Serializable {
	/**
	 * Dummy.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Em.
	 */
	@Inject
	private EntityManager em;

	/**
	 * Test method.
	 * 
	 * @return void
	 */
	public String greet() {
		return "Hello from EJB!";
	}

	/**
	 * Search users by id.
	 * 
	 * @return users.
	 */
	public List<User> searchUsers() {
		return em.createNamedQuery("User.searchByUserId", User.class)
				.setHint("org.hibernate.cacheable", true) 
				.setParameter("userId", "RENDZEL@DMV.COM").getResultList();
	}
}
