package com.alphanomad.webapp;

import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.PWA;
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
    	DBHelper dbh = new DBHelper();
    	
    	Button button = new Button("Click me",
                event -> Notification.show(dbh.php_request("signin",params,values)));
    	
        add(button);
    }
}
