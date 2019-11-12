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
//Final sprint: Hamza was here
public class AdminMainView extends VerticalLayout implements View {
	public TextField current;
	public PasswordField new_password, confirm_new, AdminPassword;
	//we create a panel to display the tab to provide user with ability to change password
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



	}*/

}
