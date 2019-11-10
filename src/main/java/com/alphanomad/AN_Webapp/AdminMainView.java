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

	@Override
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

				// lines below are used to clean the string of any extra punctuation marks
				JsonArray test = dbh.parse_json_string_arr(currPassword);
				String the_password = test.getAsJsonArray().get(0).getAsJsonArray().get(0).toString();
				the_password = the_password.substring(1, the_password.length() - 1);
				Boolean valid = true;
				//This checks whether a student with the specified student number exists. This is done by checking if the value from
				// the database is a null string(which contains "<br>")
				try {
					String[] value = { "USER_INFORMATION", "STUDENT_NUM", "STUDENT_NUM", current.getValue() };
					String student = dbh1.php_request("generic_select", par, value);
					if (student.startsWith("<br")) {
						valid = false;
						current.setComponentError(new UserError("This user does not exist."));

					}

				} catch (Exception e) {

				}

				//Checks if the student number who's password we want to change is not empty
				if (current.isEmpty()) {
					valid = false;
					current.setComponentError(new UserError("Please fill in your current password"));
				}
				//Checks if have entered a new password
				if (new_password.isEmpty()) {
					valid = false;
					new_password.setComponentError(new UserError("Please fill in your new password"));
				}
				//Checks if we have confirmed the new password
				if (confirm_new.isEmpty()) {
					valid = false;
					confirm_new.setComponentError(new UserError("Please confirm your new password"));
				}
				//Ensures that the admin has entered their password
				if (AdminPassword.isEmpty()) {
					valid = false;
					AdminPassword.setComponentError(new UserError("Please enter your password"));
				}
				//Ensures that the new password and the "confirm password" correspond
				if (new_password.isEmpty() == false && confirm_new.isEmpty()
						&& !new_password.getValue().toString().equals(confirm_new.getValue().toString())) {
					valid = false;
					confirm_new.setComponentError(new UserError("Please make sure you confirmed the correct password"));
				}

				if (new_password.isEmpty() == false && confirm_new.isEmpty() == false
						&& !new_password.getValue().equals(confirm_new.getValue())) {
					valid = false;
					new_password.setComponentError(new UserError("Passwords do not correspond"));
					confirm_new.setComponentError(new UserError("Passwords do not correspond"));
				}
				//Ensures that the admin entered their correct password
				if (AdminPassword.isEmpty() == false && !the_password.equals(AdminPassword.getValue().toString())) {
					valid = false;
					AdminPassword.setComponentError(
							new UserError("Please enter your correct password to successfully change the password"));

				}
				//If there are no irregularities, we can proceed  to actually changing the user's password
				if (valid == true) {

					String[] val2 = { "USER_INFORMATION", "STUDENT_NUM", "STUDENT_NUM", current.getValue().toString() };

					String userStudentNum = dbh1.php_request("generic_select", par, val2);

					JsonArray test2 = dbh.parse_json_string_arr(userStudentNum);
					String theStudentNum = test2.getAsJsonArray().get(0).getAsJsonArray().get(0).toString();
					theStudentNum = theStudentNum.substring(1, theStudentNum.length() - 1);

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

		// remove the password change functionality for the normal admin
		if (!info.role.equals("4")) {
			updatePassword.setVisible(false);
		}
		//Add the navigation bar
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