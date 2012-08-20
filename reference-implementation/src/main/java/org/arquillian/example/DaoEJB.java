package org.arquillian.example;

import javax.ejb.Local;

@Local
public interface DaoEJB {
	
	PlsDao getDao();
	void setDao(PlsDao plsDao);
	
	void addOrganization(String name, Integer type);
	
}

