package com.alphanomad.AN_Webapp;

import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.*;

public class TutorMainView extends VerticalLayout implements View
{

	public TutorMainView()
	{

	}

	/*@Override
	public void enter(ViewChangeEvent vc_event)
	{
		// THIS PREVENTS USERS FROM PRESSING BACK TO LOGIN WITHOUT A PASSWORD
		if (! ((MyUI)getUI()).logged_in)
		{
			getUI().getNavigator().navigateTo("login");
		} 
		
		removeAllComponents();
		DBHelper dbh = new DBHelper();

		// DEFINE COMPONENTS HERE

		Button profile_button = new Button("Profile", event -> getUI().getNavigator().navigateTo("profile"));

		String studentNum = ((MyUI) getUI()).get_user_info().get_student_num();
				
	}*/

}
