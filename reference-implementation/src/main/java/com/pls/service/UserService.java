package com.pls.service;

import java.util.List;
import com.pls.domain.User;

/**
 * User service.
 * @author User
 *
 */
public interface UserService {
	/**
	 * Get by id.
	 * @param id 
	 * @return user.
	 */
	User getById(long id);

	/**
	 * Get all.
	 * @return all users.
	 */
	List<User> getAllUsers();

	/**
	 * Save new.
	 * @param user 
	 */
	void addUser(User user);
	
	/**
	 * Search by userId.
	 * @param userId 
	 * @return users
	 */
	List<User> search(String userId);
}
