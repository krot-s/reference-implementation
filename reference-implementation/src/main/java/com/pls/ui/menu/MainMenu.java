package com.pls.ui.menu;

import javax.enterprise.inject.Any;
import javax.inject.Inject;

import org.vaadin.virkki.cdiutils.application.VaadinContext.VaadinScoped;
import org.vaadin.virkki.cdiutils.application.VaadinContext.VaadinScoped.VaadinScope;

import com.pls.ui.carrier.CarriersViewShowEvent;
import com.pls.ui.customer.CustomerViewShowEvent;
import com.pls.ui.load.LoadViewShowEvent;
import com.pls.ui.user.UserViewShowEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;

/**
 * Main application menu. 
 * @author User
 *
 */
@VaadinScoped(VaadinScope.APPLICATION)
public class MainMenu extends CustomComponent {
	private static final long serialVersionUID = 1L;
	
	@Inject @Any 
	private javax.enterprise.event.Event<CustomerViewShowEvent> customersViewEvent;
	
	@Inject @Any 
	private javax.enterprise.event.Event<CarriersViewShowEvent> carriersViewEvent;
	
	@Inject @Any 
	private javax.enterprise.event.Event<LoadViewShowEvent> loadsViewEvent;
	
	@Inject @Any 
	private javax.enterprise.event.Event<UserViewShowEvent> usersViewEvent;
	
	/**
	 * Create main menu.
	 */
	public MainMenu() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.addComponent(createMenu());
		layout.setSizeUndefined();
		setCompositionRoot(layout);
	}
		
	/**
	 * Create main menu.
	 * @return menu instance.
	 */
	@SuppressWarnings("serial")
	private MenuBar createMenu() {
		MenuBar menuBar = new MenuBar();		
		MenuBar.MenuItem plsMenu = menuBar.addItem("PLS menu", null);
		MenuBar.MenuItem carriers = plsMenu.addItem("Carriers", null);
		carriers.addItem("Search carriers", new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				carriersViewEvent.fire(new CarriersViewShowEvent());
			}
		}); 
				
		carriers.addItem("Create carrier", new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				carriersViewEvent.fire(new CarriersViewShowEvent());
			}
		}); 
				
				
		MenuBar.MenuItem customers = plsMenu.addItem("Customers", null);
		customers.addItem("Search customers", new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				customersViewEvent.fire(new CustomerViewShowEvent());
			}
		}); 
		customers.addItem("Create customer", new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				customersViewEvent.fire(new CustomerViewShowEvent());
			}
		}); 
		
		MenuBar.MenuItem loads = plsMenu.addItem("Loads", null);
		loads.addItem("Search loads", new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				loadsViewEvent.fire(new LoadViewShowEvent());
			}
		}); 
		loads.addItem("Create load", new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				loadsViewEvent.fire(new LoadViewShowEvent());
			}
		}); 
		
		MenuBar.MenuItem users = plsMenu.addItem("Users", null);
		users.addItem("Add/Search Users", new Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				usersViewEvent.fire(new UserViewShowEvent());
			}
		}); 

		
		menuBar.setSizeUndefined();
		return menuBar;
	}
}
