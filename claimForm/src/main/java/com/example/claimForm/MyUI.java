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

//import fi.jasoft.qrcode.QRCode;


/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout verticallayout = new VerticalLayout();
        final HorizontalLayout horizontallayout = new HorizontalLayout();
        
        Label label = new Label();
        label.setId("label");
        label.setCaption("Claim Form");
        
        ArrayList courses = new ArrayList();
        courses.add("COMS3003 (Formal Languages and Automata)");
        courses.add("COMS3005 (Advanced Analysis of Algorithms)");
        courses.add("COMS3009 (Software Design)");
        courses.add("COMS3010 (Operating Systems and System Programming)");
        courses.add("COMS3007 (Machine Learning)");
        courses.add("COMS3006 (Computer Graphics and Visualization)");
        courses.add("COMS3008 (Parallel Computing)");
        courses.add("COMS3011 (Software Design Project)");
        
        ArrayList activity = new ArrayList();
        activity.add("Tutoring");
        activity.add("Invigilating");
        activity.add("Marking");
        activity.add("Other");
        
        ComboBox combobox = new ComboBox("Course");
        combobox.setWidth("100%");
        combobox.setItems(courses);
        
        TextField venue = new TextField();
        venue.setCaption("Venue");
        
        ComboBox combobox2 = new ComboBox("Type of Activity");
        combobox2.setItems(activity);
        
        DateTimeField start = new DateTimeField();
        start.setCaption("Select start of duration");
        start.setValue(LocalDateTime.now());
        
        DateTimeField end = new DateTimeField();
        end.setCaption("Select end of duration");
        end.setValue(LocalDateTime.now());
        
        Button confirm = new Button("Confirm");
        //confirm.addClickListener(event -> Notification.show(combobox.getValue().toString(), Type.TRAY_NOTIFICATION));
        
        /*QRCode code = new QRCode();
        code.setValue("The quick brown fox jumps over the lazy dog");
        code.setWidth("400px");
        code.setHeight("400px");*/
        
        verticallayout.addComponents(label, combobox, venue, combobox2, start, end, confirm);
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
