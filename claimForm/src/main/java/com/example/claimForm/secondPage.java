package com.example.claimForm;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
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
public class secondPage extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout verticallayout = new VerticalLayout();
        final HorizontalLayout horizontallayout = new HorizontalLayout();
        
        Label label = new Label();
        label.setId("label");
        label.setCaption("Claim Form");
        
        
        //QRCode code = new QRCode();
        //code.setValue("The quick brown fox jumps over the lazy dog");
        
        //verticallayout.addComponents(label, combobox, venue, combobox2, start, end, confirm);
        /*Button button = new Button("Register", e -> UI.getCurrent().navigate(Register.class));
         * add(button);
         */
        
        setContent(verticallayout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
