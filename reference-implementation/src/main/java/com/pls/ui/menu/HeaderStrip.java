package com.pls.ui.menu;

import javax.enterprise.inject.Any;
import javax.inject.Inject;

import org.vaadin.virkki.cdiutils.application.VaadinContext.VaadinScoped;
import org.vaadin.virkki.cdiutils.application.VaadinContext.VaadinScoped.VaadinScope;

import com.pls.ui.PlsApplication;
import com.pls.ui.carrier.CarriersViewShowEvent;
import com.pls.ui.customer.CustomerViewShowEvent;
import com.pls.ui.load.LoadViewShowEvent;
import com.pls.ui.user.UserViewShowEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;

/**
 * Application header (with menu).
 * 
 * @author User
 *
 */
@VaadinScoped(VaadinScope.APPLICATION)
public class HeaderStrip extends CustomComponent {
	private static final long serialVersionUID = 1L;

	@Inject
	private PlsApplication application;
	
	@Inject @Any 
	private javax.enterprise.event.Event<CustomerViewShowEvent> customersViewEvent;
	
	@Inject @Any 
	private javax.enterprise.event.Event<CarriersViewShowEvent> carriersViewEvent;
	
	@Inject @Any 
	private javax.enterprise.event.Event<LoadViewShowEvent> loadsViewEvent;
	
	@Inject @Any 
	private javax.enterprise.event.Event<UserViewShowEvent> usersViewEvent;
	
	/**
	 * Create new header. 
	 * @param mainMenu 
	 */
	@SuppressWarnings("serial")
	@Inject
	public HeaderStrip(MainMenu mainMenu) {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.addComponent(mainMenu);

		layout.addComponent(new Button("Financial Dashboard",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						getWindow().showNotification(
								"Financial Dashboard click");
					}
				}));

		layout.addComponent(new Button("Carriers", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				carriersViewEvent.fire(new CarriersViewShowEvent());
			}
		}));

		layout.addComponent(new Button("Customers", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				customersViewEvent.fire(new CustomerViewShowEvent());
			}
		}));

		layout.addComponent(new Button("Users", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				usersViewEvent.fire(new UserViewShowEvent());
			}
		}));

		layout.addComponent(new Button("Logout", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				application.closeApplication();
			}
		}));

		layout.setSizeUndefined();
		setCompositionRoot(layout);
	}
}