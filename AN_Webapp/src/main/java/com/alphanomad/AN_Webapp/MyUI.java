package com.alphanomad.AN_Webapp;

import javax.servlet.annotation.WebServlet;

import com.alphanomad.AN_Webapp.DBHelper;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
	
	Navigator navigator;
	protected static final String MAINVIEW = "main";
	protected static final String PROFILEVIEW = "profile";
	protected static final String LOGINVIEW="login";
	protected static final String REGVIEW="register";
	protected static final String HISTORYVIEW="history";


    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("Navigation Example");
    	addStyleName("image-backgound");

        // Create a navigator to control the views
        navigator = new Navigator(this, this);

        // Create and register the views
        navigator.addView(MAINVIEW, new MainView());
        navigator.addView(PROFILEVIEW, new ProfileView());
        navigator.addView(LOGINVIEW, new LoginView());
        navigator.addView(REGVIEW, new Register());
        navigator.addView(HISTORYVIEW,new ClaimHistory());
        
        
        navigator.navigateTo(MAINVIEW);
    }
        

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = true)
    public static class MyUIServlet extends VaadinServlet {
    }
}
