package com.vaadin.vaadin_archetype_application;

import com.vaadin.flow.component.dependency.HtmlImport;
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
@PWA(name = "My Application", shortName = "My Application")
public class MainView extends VerticalLayout {

    public MainView() {
    	String[] params = {"student_num","password"} ;
    	String[] values = {"1","test"};
        Button button = new Button("Click me",
                event -> Notification.show(php_request("signin",params,values)));
        
        add(button);
    }
    
    private String login(String user_id, String user_pw)
    {
    	DBHelper dbh = new DBHelper();
    	return dbh.test_req();
    }
    
    private String php_request(String endpoint, String[] params, String[] values)
    {
    	DBHelper dbh = new DBHelper();
    	return dbh.php_request(endpoint, params, values);
    }
}
