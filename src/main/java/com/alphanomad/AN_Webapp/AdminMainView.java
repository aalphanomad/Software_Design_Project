package com.alphanomad.AN_Webapp;

import com.google.gson.JsonArray;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class AdminMainView extends VerticalLayout implements View {
	public TextField current;
	public PasswordField new_password, confirm_new, AdminPassword;

	Panel p = new Panel();

	public AdminMainView() {

	}

	/*@Override
	public void enter(ViewChangeEvent vc_event) {

		UserInfo info = ((MyUI) getUI()).get_user_info();
		// This prevents the user from attempting to go back once they have logged out
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
		// the size adjustments are made to have the bar span the whole page length
		view_application_btn.setWidth("60%");
		view_users_btn.setWidth("60%");
		view_courses_btn.setWidth("60%");
		login.setWidth("60%");
		title.setWidth("240%");

		HorizontalLayout password_line = new HorizontalLayout();

		// create a button strictly only for super-admin to change any user's password
		Button updatePassword = new Button("Change User's Password", event1 -> {

			p.setHeight("310px");
			p.setWidthUndefined();
			p.setVisible(true);

			password_line.addComponent(p);

			FormLayout fl = new FormLayout();
			fl.setMargin(true);

			// the super-admin fills in the student number of the user whose password needs
			// to be changed
			current = new TextField();
			current.setCaption("Enter Users' Student Number:");
			fl.addComponent(current);

			// the super-admin fills in the new password of the user
			new_password = new PasswordField();
			new_password.setCaption("Enter New Password:");
			fl.addComponent(new_password);

			// the super-admin fills in the new password again for confirmation of the
			// password
			confirm_new = new PasswordField();
			confirm_new.setCaption("Confirm New Password:");
			fl.addComponent(confirm_new);

			// the super-admin needs to enter his/her password for security purposes
			AdminPassword = new PasswordField();
			AdminPassword.setCaption("Enter Admin's Password:");
			fl.addComponent(AdminPassword);

			// button below will trigger the action to update the password on the database
			DBHelper dbh = new DBHelper();
			Button confirmPass = new Button("Confirm Password", event2 -> {

				// we use the generic php to get the super-admin's current password
				// so that we can check if the inputs is the correct password
				current.setComponentError(null);
				new_password.setComponentError(null);
				confirm_new.setComponentError(null);
				AdminPassword.setComponentError(null);
				DBHelper dbh1 = new DBHelper();
				String student_num = ((MyUI) getUI()).get_user_info().get_student_num();
				String[] par = { "table", "target", "filter", "value" };
				String[] val = { "USER_INFORMATION", "USER_PASSWORD", "STUDENT_NUM", student_num };

				String currPassword = dbh1.php_request("generic_select", par, val);


	}*/

}