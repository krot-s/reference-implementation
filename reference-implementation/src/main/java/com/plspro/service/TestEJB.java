package com.plspro.service;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

@SessionScoped
public class TestEJB implements Serializable {
	public String greet(){
		return "Hello from EJB!";
	}
}
