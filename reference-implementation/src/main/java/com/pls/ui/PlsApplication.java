package com.pls.ui;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;

import org.vaadin.virkki.cdiutils.application.AbstractCdiApplication;
import org.vaadin.virkki.cdiutils.application.AbstractCdiApplicationServlet;
import org.vaadin.virkki.cdiutils.application.AbstractCdiApplicationServlet.ApplicationClass;

import com.pls.scheduler.ShedulerExample;
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
	
	/**
	 * Class used to map URL to servlet.
	 * @author User
	 *
	 */
	@SuppressWarnings("serial")
	@WebServlet(urlPatterns = "/*")
    @ApplicationClass(PlsApplication.class)
    public static class ApplicationServlet extends AbstractCdiApplicationServlet {
	}
	
	@Inject @Any 
	private Event<UserViewShowEvent> event;
	
	/**
	 * TODO: remove this. This is for demonstration purpose only.
	 */
	@Inject
	private ShedulerExample e;

	@Override
	public void init() {
		initMainWindow();		
	}
	
	/**
	 * Init main window.
	 */
	private void initMainWindow() {
		e.run();
		
		setTheme("runo");
		setMainWindow(new Window("Vaadin Application"));		
		getMainWindow().addListener(new Window.CloseListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void windowClose(CloseEvent e) {
				closeApplication();
			}
		});
		setLogoutURL("/");
		
		event.fire(new UserViewShowEvent());
	}
	
	/**
	 * Close application instance and logout.
	 */
	public void closeApplication() {
//		session.invalidate();
		close();
	}
}
