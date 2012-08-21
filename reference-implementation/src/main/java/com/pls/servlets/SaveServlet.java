package com.pls.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pls.domain.User;
import com.pls.service.UserService;
import com.pls.service.UserServiceImpl;

/**
 * Servlet implementation class SaveServlet.
 * 
 */
@WebServlet("/save")
public class SaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private UserService service;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Get impl.
     * 
     * @param request 
     * @param response 
     * 
     * @throws IOException 
     * @throws ServletException  
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		User user = new User();
		user.setCreatedBy(1L);
		user.setDateCreated(new Date());
		user.setDateModified(new Date());
		user.setEmailAddress("eml@eml.ru");
		user.setFirstName("vasya");
		user.setLastName("pupkin");
		user.setModifiedBy(1L);
		user.setPassword("vasya");
		user.setUserId(UUID.randomUUID().toString());
		user.setStatus("A");
		service.addUser(user);
		
		response.getWriter().print("<h1>User created</h1>");
	}


}
