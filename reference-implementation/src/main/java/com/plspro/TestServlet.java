package com.plspro;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;

import com.plspro.service.TestEJB;

/**
 * Test servlet.
 * 
 * @author User
 *
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	/**
	 * dummy.
	 */
	private static final long serialVersionUID = 1L;
		
	/**
	 * test service.
	 */
	@Inject
	private TestEJB bean;
 
	
	/**
	 * Test method.
	 * @param request 
	 * @param response 
	 * @throws ServletException 
	 * @throws IOException 
	 */
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) 
	throws ServletException, IOException {
		LoggerFactory.getLogger(TestServlet.class).debug("asdfasdfas df");
		
		response.getWriter().print("<h1>Users count: " + bean.searchUsers().size());		
	}

}
