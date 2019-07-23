package com.vaadin.vaadin_archetype_application;

import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.vaadin_archetype_application.DBHelper;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route

public class ProfileView extends VerticalLayout {

    public ProfileView() {
    	String stud_num = "1";
    	String[] params = {"student_num"} ;
    	String[] values = {stud_num};
    	DBHelper dbh = new DBHelper();
    	String name = dbh.php_request("get_name", params, values);
    	System.out.println("HERE\nHERE\nHERE\n" + name);
    

        
    	add(new H2(name));
    	add(new H1(stud_num));
    }
   
    /**
     * creates a DBHelper and runs the php_request method in that 
     * see the DBHelper Class 
	 * @see DBHelper#php_request(String, String[], String[])
     */
    private String php_request(String endpoint, String[] params, String[] values)
    {
    	DBHelper dbh = new DBHelper();
    	return dbh.php_request(endpoint, params, values);
    }
}
