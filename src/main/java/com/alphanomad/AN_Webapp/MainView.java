package com.alphanomad.AN_Webapp;

import com.google.gson.JsonObject;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.*;


public class MainView extends VerticalLayout implements View{
	
	public MainView() {

	    String[] params = {"student_num","password"} ;
		String[] values = {"1","test"};
		DBHelper dbh = new DBHelper();
		
	    
	    Button profile_button = new Button("go to profile view",
	            event -> getUI().getNavigator().navigateTo("profile"));
	    
	    addComponent(profile_button);
	    
	    String studentNum="1";
        
        String[] parameters= { "table", "target", "student_num" };
		String[] valuess = { "BOOKINGS", "*", studentNum };
		
		
		Button pdf_button = new Button("Generate PDF",
				
				
				event -> UI.getCurrent().getPage().open(dbh.php_request("select_booking", parameters, valuess),"_blank")
				//event -> Notification.show(dbh.php_request("select_booking", parameters, valuess))
				
				);
		
		Button Login=new Button("Login",event->getUI().getNavigator().navigateTo("login"));
		addComponent(Login);
		Button Register=new Button("Register",event->getUI().getNavigator().navigateTo("register"));
		addComponent(Register);
		addComponent(pdf_button);
    }
	
}

