package com.alphanomad.AN_Webapp;

import com.google.gson.JsonObject;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class MainView extends VerticalLayout implements View{
	
	public MainView() {

	    String[] params = {"student_num","password"} ;
		String[] values = {"1","test"};
		DBHelper dbh = new DBHelper();
		
		Button button = new Button("Click me",
	            event -> Notification.show(dbh.php_request("signin",params,values)));
		
	    addComponent(button);
	    
	    Button profile_button = new Button("go to profile view",
	            event -> getUI().getNavigator().navigateTo("profile"));
	    
	    addComponent(profile_button);
    }
	
}


