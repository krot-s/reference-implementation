package com.plspro.service;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

/**
 * Test EJB.
 * 
 * @author User
 *
 */
@SessionScoped
public class TestEJB implements Serializable {

	public String greet() {
		return "Hello from EJB!";
	}
}
