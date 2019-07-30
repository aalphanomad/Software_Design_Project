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
	String course;
    String venue;
    String activity;

    public MyUI () {
        final VerticalLayout verticallayout = new VerticalLayout();
        final HorizontalLayout horizontallayout = new HorizontalLayout();
        
        Label label = new Label();
        label.setId("label");
        label.setCaption("Claim Form");
        
        ArrayList coursesArray = new ArrayList();
        coursesArray.add("COMS3003 (Formal Languages and Automata)");
        coursesArray.add("COMS3005 (Advanced Analysis of Algorithms)");
        coursesArray.add("COMS3009 (Software Design)");
        coursesArray.add("COMS3010 (Operating Systems and System Programming)");
        coursesArray.add("COMS3007 (Machine Learning)");
        coursesArray.add("COMS3006 (Computer Graphics and Visualization)");
        coursesArray.add("COMS3008 (Parallel Computing)");
        coursesArray.add("COMS3011 (Software Design Project)");
        
        ArrayList activityArray = new ArrayList();
        activityArray.add("Tutoring");
        activityArray.add("Invigilating");
        activityArray.add("Marking");
        activityArray.add("Other");
        
        ComboBox combobox = new ComboBox("Course");
        combobox.setWidth("100%");
        combobox.setItems(coursesArray);
        
        TextField textfield = new TextField();
        textfield.setCaption("Venue");
        
        ComboBox combobox2 = new ComboBox("Type of Activity");
        combobox2.setItems(activityArray);
        
        DateTimeField start = new DateTimeField();
        start.setCaption("Select start of duration");
        start.setValue(LocalDateTime.now());
        
        DateTimeField end = new DateTimeField();
        end.setCaption("Select end of duration");
        end.setValue(LocalDateTime.now());
        
        
        /*QRCode code = new QRCode();
        code.setValue("The quick brown fox jumps over the lazy dog");
        code.setWidth("400px");
        code.setHeight("400px");*/
        
        /*Button button = new Button("Register", e -> UI.getCurrent().navigate(Register.class));
         * add(button);
         */
        
        if(!combobox.isEmpty() && !textfield.isEmpty() && !combobox2.isEmpty()) {
	        course = combobox.getValue().toString();
	        venue = textfield.getValue().toString();
	        activity = combobox2.getValue().toString();
        }
        
        else {
        	combobox.setPlaceholder("Please fill in");
        	textfield.setPlaceholder("Please fill in");
        	combobox2.setPlaceholder("Please fill in");
        }
        String string = start.getValue().toString();
        String timeStart = string.substring(11, string.length()-13);
        String string2 = end.getValue().toString();
        String timeEnd = string2.substring(11, string2.length()-13);
        
        Button confirm = new Button("Confirm");
        confirm.addClickListener(event -> Notification.show(timeStart, Type.TRAY_NOTIFICATION));
        
        verticallayout.addComponents(label, combobox, textfield, combobox2, start, end, confirm);
        setContent(verticallayout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

	@Override
	protected void init(VaadinRequest request) {
		// TODO Auto-generated method stub
		
	}
}
