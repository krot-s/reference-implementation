package org.arquillian.example;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;


@SessionScoped
public class DaoEjbWraper implements Serializable {

	private static final long serialVersionUID = 2127657759051278744L;
	
	@EJB
	private DaoEJB daoEjb;
	
	
	public DaoEJB getDaoEjb() {
		return daoEjb;
	}


	public void setDaoEjb(DaoEJB daoEjb) {
		this.daoEjb = daoEjb;
	}


	public void addOrganization(String name, Integer type) {
		daoEjb.addOrganization(name, type);
	}
	
}
