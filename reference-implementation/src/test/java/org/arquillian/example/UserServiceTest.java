package org.arquillian.example;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.pls.domain.User;
import com.pls.service.UserService;
import com.pls.service.UserServiceImpl;


/**
 * Dummy. Fix chesktyle rules!
 * 
 * @author User
 *
 */
@RunWith(Arquillian.class)
public class UserServiceTest {
	/**
	 * Dummy. Fix chesktyle rules!
	 * @return zsdf
	 */
    @Deployment
    public static Archive<?> createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
        	.addClasses(UserService.class, UserServiceImpl.class, User.class)
            .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
        return jar;
    }
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserService userService;
    
    /**
     * Dummy. Fix chesktyle rules! 
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
	@Test
    public void testAddUser() throws Exception {
    	User user = new User();
    	user.setCreatedBy(1L);
    	user.setDateCreated(new Date());
    	user.setDateModified(new Date());
    	user.setEmailAddress("user@user.ua");
    	user.setFirstName("firstName");
    	user.setLastName("lastName");
    	user.setModifiedBy(1L);
    	user.setPassword("password");
    	user.setStatus("A");
    	user.setUserId(UUID.randomUUID().toString());
    	userService.addUser(user);
    	
    	em.clear();
    	List<User> users = em.createQuery("from User where userId = :userId")
    		.setParameter("userId", user.getUserId()).getResultList();
    	assertNotNull(users);
    	assertEquals(1, users.size());
    	assertEquals(user.getUserId(), users.get(0).getUserId());
    }
}