package org.arquillian.example;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

@Singleton
@Lock(LockType.READ)
public class DaoEJBBean implements DaoEJB {

	private PlsDao dao;
	
	public PlsDao getDao() {
		return dao;
	}

	public void setDao(PlsDao dao) {
		this.dao = dao;
	}

	@Override
	public void addOrganization(String name, Integer type) {
		dao.addOrganization(name, type);
		
	}

}
