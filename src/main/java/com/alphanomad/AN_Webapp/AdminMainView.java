package com.alphanomad.AN_Webapp;

import java.util.ArrayList;
import java.util.Set;

import org.apache.commons.lang.WordUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.UserError;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.ImageRenderer;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class AdminMainView extends VerticalLayout implements View {
	
	Panel p = new Panel();

	public AdminMainView() {

	}

	@Override
	public void enter(ViewChangeEvent vc_event) {
		
		UserInfo info = ((MyUI) getUI()).get_user_info();
		// THIS PREVENTS USERS FROM PRESSING BACK TO LOGIN WITHOUT A PASSWORD
		if (!((MyUI) getUI()).logged_in) {
			getUI().getNavigator().navigateTo("login");
			return;
		}

		// setting title for navigation bar and theme
		Label title = new Label("Menu");
		title.addStyleName(ValoTheme.MENU_TITLE);

		// empty the screen just in case
		components.clear();
		Button view_application_btn = new Button("view pending applications",
				btn_event -> getUI().getNavigator().navigateTo("coursealloc"));
		Button view_users_btn = new Button("view all users",
				btn_event -> getUI().getNavigator().navigateTo("tutorlist"));
		Button view_courses_btn = new Button("view all courses",
				btn_event -> getUI().getNavigator().navigateTo("courselist"));
		Button login = new Button("Logout", event -> {
			getUI().getNavigator().navigateTo("login");
		});

		
		// add the following buttons to the navigation bar
		//the size adjustments are made to have the bar span the whole page length
		view_application_btn.setWidth("60%");
		view_users_btn.setWidth("60%");
		view_courses_btn.setWidth("60%");
		login.setWidth("60%");
		title.setWidth("240%");
		

		HorizontalLayout password_line = new HorizontalLayout();
	
		//create a button strictly only for super-admin to change any user's password
		Button updatePassword = new Button("Change User's Password", event1 -> {
			p.setHeight("310px");
			p.setWidthUndefined();
			p.setVisible(true);

			password_line.addComponent(p);

			FormLayout fl = new FormLayout();
			fl.setMargin(true);

			//the super-admin fills in the student number of the user whose password needs to be changed
			TextField current = new TextField();
			current.setCaption("Enter Users' Student Number:");
			fl.addComponent(current);

			//the super-admin fills in the new password of the user 
			PasswordField new_password = new PasswordField();
			new_password.setCaption("Enter New Password:");
			fl.addComponent(new_password);

			//the super-admin fills in the new password again for confirmation of the password 
			PasswordField confirm_new = new PasswordField();
			confirm_new.setCaption("Confirm New Password:");
			fl.addComponent(confirm_new);

			//the super-admin needs to enter his/her password for security purposes
			PasswordField AdminPassword = new PasswordField();
			AdminPassword.setCaption("Enter Admin's Password:");
			fl.addComponent(AdminPassword);

			//button below will trigger the action to update the password on the database
			DBHelper dbh = new DBHelper();
			Button confirmPass = new Button("Confirm Password", event2 -> {
				
				
				
				//we use the generic php to get the super-admin's current password 
				//so that we can check if the inputs is the correct password
				DBHelper dbh1 = new DBHelper();
				String[] par = { "table", "target", "filter", "value" };
				String[] val = { "USER_INFORMATION", "USER_PASSWORD", "USER_PASSWORD",
						AdminPassword.getValue().toString() };

				String currPassword = dbh1.php_request("generic_select", par, val);

				JsonArray test = dbh.parse_json_string_arr(currPassword);
				String the_password = test.getAsJsonArray().get(0).getAsJsonArray().get(0).toString();
				the_password = the_password.substring(1, the_password.length() - 1);

				String[] val2 = { "USER_INFORMATION", "STUDENT_NUM", "STUDENT_NUM", current.getValue().toString() };

				String userStudentNum = dbh1.php_request("generic_select", par, val2);

				JsonArray test2 = dbh.parse_json_string_arr(userStudentNum);
				String theStudentNum = test2.getAsJsonArray().get(0).getAsJsonArray().get(0).toString();
				theStudentNum = theStudentNum.substring(1, theStudentNum.length() - 1);
				 				//throw the following user-errors if the user does not fill their details correctly
								if(current.isEmpty()) {
				 					current.setComponentError(new UserError("Please fill in your current password"));
				 				}
				 				
				 				else if(new_password.isEmpty()) {
				 					new_password.setComponentError(new UserError("Please fill in your new password"));
				 				}
				 				
				 				else if(confirm_new.isEmpty()) {
				 					confirm_new.setComponentError(new UserError("Please confirm your new password"));
								}
				 				
								else if(AdminPassword.isEmpty()) {
				 					AdminPassword.setComponentError(new UserError("Please enter your password"));
								}
				 				
				 				else if(!new_password.getValue().toString().equals(confirm_new.getValue().toString())) {
				 					confirm_new.setComponentError(new UserError("Please make sure you confirmed the correct password"));
								}
				 				
				 				else if(!the_password.equals(AdminPassword.getValue().toString())) {
									AdminPassword.setComponentError(new UserError("Please enter your correct password to successfully change the password"));
				 				}
				 				
				 				else if (the_password.equals(AdminPassword.getValue().toString())
					  						&& new_password.getValue().toString().equals(confirm_new.getValue().toString())
					  						&& current.getValue().toString().equals(theStudentNum)) {
					String[] params = { "password", "student_num" };

					String[] values = { confirm_new.getValue().toString(), theStudentNum };
					dbh1.php_request("update_password", params, values);
					Notification.show("Password changed Successfully");
					p.setVisible(false);
				} 

			});

			fl.addComponent(confirmPass);
			p.setContent(fl);
		});

		password_line.addComponent(updatePassword);
		
		//remove the password change functionality for the normal admin
		if(!info.role.equals("4")) {
			updatePassword.setVisible(false);
		}

		CssLayout menu = new CssLayout(title, view_application_btn, view_users_btn, view_courses_btn, login);
		menu.addStyleName(ValoTheme.MENU_ROOT);

		// certain css layout to apply to navigation bar
		CssLayout viewContainer = new CssLayout();
		// adding the navigation bar to the page
				HorizontalLayout mainLayout = new HorizontalLayout(menu, viewContainer);
				mainLayout.setSizeFull();
				addComponent(mainLayout);
		       		addComponent(password_line);

			}

		}