package com.pls.ui;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;

import org.vaadin.virkki.cdiutils.application.AbstractCdiApplication;
import org.vaadin.virkki.cdiutils.application.AbstractCdiApplicationServlet;
import org.vaadin.virkki.cdiutils.application.AbstractCdiApplicationServlet.ApplicationClass;

import com.pls.ui.user.UserViewShowEvent;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;

/**
 * Application entry point.
 * 
 * @author User
 *
 */
public class PlsApplication extends AbstractCdiApplication {
	private static final long serialVersionUID = 1L;
	
	@Inject @Any 
	private Event<UserViewShowEvent> event;

	private String userRole;
	
	@Override
	public void init() {
		initMainWindow();		
	}
	
	/**
	 * Init main window.
	 */
	private void initMainWindow() {
		setTheme("runo");
		setMainWindow(new Window("Vaadin Application"));		
		getMainWindow().addListener(new Window.CloseListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void windowClose(CloseEvent e) {
				closeApplication();
			}
		});
//		setLogoutURL("/");
		
		event.fire(new UserViewShowEvent());
	}
	
	/**
	 * Close application instance and logout.
	 */
	public void closeApplication() {
//		session.invalidate();
		close();
	}
	
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
}
