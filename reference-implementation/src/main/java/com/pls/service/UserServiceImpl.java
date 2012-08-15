package com.pls.service;

import java.util.Date;
import java.util.List;
import java.util.Vector;

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
	private static final int MAX_RESULTS_PER_PAGE = 10;
	private static final int MAGIC_NUMBER = 50;

	@Inject
	private EntityManager em;

	private final List<User> users;

	/**
	 * Default constructor.
	 */
	public UserServiceImpl() {
		users = new Vector<User>(MAGIC_NUMBER);
		for (long i = 0; i < MAGIC_NUMBER; i++) {
			users.add(createUser(i));
		}
	}

	@Override
	public User getById(User id) {
		for (User user : users) {
			if (user.getPersonId().equals(id)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		return users;
	}

	@Override
	public void addUser(User user) {
		users.add(user);
	}

	/**
	 * New instance.
	 * @param id 
	 * @return new instance.
	 */
	private static User createUser(Long id) {
		User user = new User();
		user.setPersonId(id);
		user.setCreatedBy((long) 0);
		user.setDateCreated(new Date());
		user.setDateModified(new Date());
		user.setEmailAddress("mail@gmail.com");
		user.setFirstName("Alex");
		user.setLastName("Dos");
		user.setModifiedBy((long) 0);
		user.setPassword("*********");
		user.setStatus("off");
		return user;
	}

	@Override
	public List<User> search(String str) {
		return searchUsers(str);
	}

	/**
	 * Search users by userId.
	 * 
	 * @param userId 
	 * 
	 * @return users.
	 */
	public List<User> searchUsers(String userId) {
		return PersistanceUtils.cacheQuery(
					em.createNamedQuery("User.searchByUserId", User.class)
					.setParameter("userId", userId))
					.setMaxResults(MAX_RESULTS_PER_PAGE
				).getResultList();
	}
}
