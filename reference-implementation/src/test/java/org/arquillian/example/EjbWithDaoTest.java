package org.arquillian.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(Arquillian.class)
public class EjbWithDaoTest {
    @Deployment
    public static Archive<?> createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
            .addPackage(DaoEJBBean.class.getPackage())
            .addAsManifestResource("test-persistence.xml", "persistence.xml")
            .addAsManifestResource("jbossas-ds.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        
        // choose your packaging here
        return jar;
    }
    
    @PersistenceContext
    EntityManager em;
    
    @EJB
    DaoEJB daoEjb;
    
    @Inject
    DaoEjbWraper daoEjbWrapper;
    
    @Inject
    PlsDaoImpl plsDao;
    
    private DataSource dataSource;
    
    @Before
    public void prepare() throws NamingException {
        // XXX
    	InitialContext cxt = new InitialContext();

    	dataSource = (DataSource) cxt.lookup( "jdbc/arquillian" );
    	
    	plsDao.setDataSource(dataSource);
    	daoEjb.setDao(plsDao);
    }
    
    @Test
    public void testEjb() throws Exception {

    	daoEjbWrapper.addOrganization("NEW ORGANIZATION", 1);
    	
    	
    	Connection c = dataSource.getConnection();
    	Statement s = c.createStatement();
    	ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM ORGANIZATION WHERE org_name='NEW ORGANIZATION';");

    	if (rs.next()) {
    		assert(rs.getInt(1) == 1);
    	} else {
    		throw new Exception("Failed to select NEW ORGANIZATION");
    	}
    }
    
}