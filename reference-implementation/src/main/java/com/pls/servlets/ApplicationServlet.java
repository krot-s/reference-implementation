package com.pls.servlets;

import java.security.Principal;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.vaadin.virkki.cdiutils.application.AbstractCdiApplication;
import org.vaadin.virkki.cdiutils.application.AbstractCdiApplicationServlet;
import org.vaadin.virkki.cdiutils.application.AbstractCdiApplicationServlet.ApplicationClass;

import com.pls.security.UserRoles;
import com.pls.ui.PlsApplication;
import com.vaadin.Application;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/ui/*", "/VAADIN/*"})
@ApplicationClass(PlsApplication.class)
public class ApplicationServlet extends AbstractCdiApplicationServlet {

	@Inject
	private Instance<PlsApplication> application;
	
	@Override
	protected AbstractCdiApplication getNewApplication(HttpServletRequest request) throws	ServletException {
		PlsApplication app = application.get();
		Principal principal = request.getUserPrincipal();
		if (principal == null) {
			throw new ServletException("Access denied");
		}
		
		if (request.isUserInRole(UserRoles.USER_ROLE.toString())) {
			app.setUserRole(UserRoles.USER_ROLE.toString());
		} else {
			throw new ServletException("Access denied");
		}
		
		app.setUser(principal);
		app.setLogoutURL(request.getContextPath() + "/logout.jsp");
		
		return app;
	}
}
