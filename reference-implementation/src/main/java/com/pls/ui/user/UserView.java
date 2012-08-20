package com.pls.ui.user;

import java.io.Serializable;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.vaadin.virkki.cdiutils.application.VaadinContext.VaadinScoped;
import org.vaadin.virkki.cdiutils.application.VaadinContext.VaadinScoped.VaadinScope;

import com.pls.domain.User;
import com.pls.service.UserService;
import com.pls.ui.components.CustomFormFieldFactory;
import com.pls.ui.components.CustomTableFieldFactory;
import com.pls.ui.menu.HeaderStrip;
import com.vaadin.Application;
import com.vaadin.addon.beanvalidation.BeanValidationForm;
import com.vaadin.addon.tableexport.ExcelExport;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

/**
 * View for users screen. 
 * @author User
 *
 */
@VaadinScoped(VaadinScope.APPLICATION)
public class UserView implements Serializable {
	private static final long serialVersionUID = -7921194304196825061L;

	@Inject
	private Application application;

	@Inject
	private UserService service;

	@Inject
	private HeaderStrip hearder;

	/**
	 * Show view.
	 * @param event 
	 */
	public void showView(@Observes UserViewShowEvent event) {
		initLayout();
	}

	/**
	 * Init layout. 
	 */
	@SuppressWarnings("serial")
	private void initLayout() {
		VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.addComponent(hearder);
		layout.setDebugId("UserView.initLayout.layout");

		final BeanContainer<Long, User> beans = new BeanContainer<Long, User>(
				User.class);
		beans.setBeanIdProperty("personId");
		beans.addAll(service.getAllUsers());

		final BeanValidationForm<User> form = new BeanValidationForm<User>(
				User.class);
		form.setImmediate(false);
		form.setCaption("Create new User");
		setDataSource(form);
		form.removeItemProperty("personId");
		Button addButton = new Button("Create");
		addButton.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					form.validate();
					service.addUser((User) form.getData());
					setDataSource(form);
					beans.addAll(service.getAllUsers());
				} catch (InvalidValueException e) {
					MessageBox mb = new MessageBox(application.getMainWindow(),
							"Error", MessageBox.Icon.ERROR,
							"Please correct form errors and try again",
							new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
					mb.show();
				}
			}
		});

		final Table table = createTable(beans);
		final Button excelExportButton = new Button("Export to Excel");

		excelExportButton.addListener(new Button.ClickListener() {

			public void buttonClick(final ClickEvent event) {
				ExcelExport excelExport = new ExcelExport(table);
				excelExport.excludeCollapsedColumns();
				excelExport.setReportTitle("Demo Report");
				excelExport.export();
			}
		});

		Label searchlabel = new Label("User ID");
		final TextField searchField = new TextField();
		Button searchButton = new Button("Search");
		searchButton.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				beans.removeAllItems();
				beans.addAll(service.search(
						searchField.getValue().toString().replaceAll("\\*", "%")));
			}
		});

		HorizontalLayout panel = new HorizontalLayout();
		panel.setMargin(true);
		panel.setCaption("Search");
		panel.addComponent(searchlabel);
		panel.addComponent(searchField);
		panel.addComponent(searchButton);

		HorizontalLayout buttons = new HorizontalLayout();
		buttons.addComponent(addButton);
		buttons.addComponent(excelExportButton);
		form.getFooter().addComponent(buttons);
		layout.addComponent(panel);
		layout.addComponent(form);
		layout.addComponent(table);

		application.getMainWindow().setContent(layout);
	}

	/**
	 * Set datasource for form. 
	 * @param form 
	 */
	private void setDataSource(Form form) {
		User user = new User();
		form.setData(user);
		form.setFormFieldFactory(new CustomFormFieldFactory());
		form.setItemDataSource(new BeanItem<User>(user));
	}
	
	/**
	 * Create new table for beans.
	 * @param beans 
	 * @return new table instance.
	 */
	private Table createTable(BeanContainer<Long, User> beans) {
		final Table table = new Table();
		table.setWidth("100%");
		table.setDebugId("UserView.initLayout.table");
		table.setColumnReorderingAllowed(true);
		table.setEditable(true);
		table.setSelectable(true);
		table.setContainerDataSource(beans);
		table.setTableFieldFactory(new CustomTableFieldFactory<User>(User.class));
		table.setColumnHeader("createdBy", "Created By");
		table.setColumnHeader("dateCreated", "Date Created");
		table.setColumnHeader("dateModified", "Date Modified");
		table.setColumnHeader("emailAddress", "Email Address");
		table.setColumnHeader("firstName", "First Name");
		table.setColumnHeader("lastName", "Last Name");
		table.setColumnHeader("modifiedBy", "Modified By");
		table.setColumnHeader("password", "Password");
		table.setColumnHeader("status", "Status");
		table.setColumnHeader("id", "User Id");
		return table;
	}

}
