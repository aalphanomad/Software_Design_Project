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
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.ImageRenderer;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class AdminMainView extends VerticalLayout implements View
{

	public AdminMainView()
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
		
		// empty the screen just in case
		components.clear();
		Button view_application_btn = new Button("view pending applications",
				btn_event -> getUI().getNavigator().navigateTo("coursealloc"));
		Button view_users_btn = new Button("view all users",
				btn_event -> getUI().getNavigator().navigateTo("tutorlist"));
		Button view_courses_btn = new Button("view all courses",
				btn_event -> getUI().getNavigator().navigateTo("courselist"));
		Button login = new Button("Logout", event -> 
		 {
			 getUI().getNavigator().navigateTo("login");	
		});
		addComponent(view_application_btn);
		addComponent(view_users_btn);
		addComponent(view_courses_btn);
		addComponent(login);
	}*/

}
