package com.alphanomad.AN_Webapp;

import com.google.gson.JsonObject;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.*;

public class MainView extends VerticalLayout implements View
{

	public MainView()
	{

	}

	/*@Override
	public void enter(ViewChangeEvent vc_event)
	{
		// if(!((MyUI)getUI()).logged_in)
		// {
		// getUI().getNavigator().navigateTo("login");
		// }
		removeAllComponents();
		DBHelper dbh = new DBHelper();

		// DEFINE COMPONENTS HERE
		Button profile_button = new Button("Profile", event -> getUI().getNavigator().navigateTo("profile"));

		String studentNum = ((MyUI) getUI()).get_user_info().get_student_num();

		String[] parameters = { "table", "target", "student_num" };
		String[] valuess = { "BOOKINGS", "*", studentNum };
		Button pdf_button = new Button("Generate Claims Form", event -> UI.getCurrent().getPage()
				.open(dbh.php_request("select_booking", parameters, valuess), "_blank"));

	}*/

}
