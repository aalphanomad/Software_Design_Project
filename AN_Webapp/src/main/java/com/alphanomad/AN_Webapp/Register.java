package com.alphanomad.AN_Webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.vaadin.addons.ComboBoxMultiselect;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.View;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;


@Theme("mytheme")
public class Register extends VerticalLayout implements View{
	
	TextField Name,StudentNumber;
	PasswordField Password,ConfPassword;
	public void TheRegister() {
		
		Name.setComponentError(null);
		StudentNumber.setComponentError(null);
		Password.setComponentError(null);;
		ConfPassword.setComponentError(null);
		
		if(Name.isEmpty()) {
			Name.setComponentError(new UserError("Please Enter Your Name"));
		}
		if(StudentNumber.isEmpty()) {
			StudentNumber.setComponentError(new UserError("Please Enter Your Student Number"));
		}
		if(!Password.getValue().equals(ConfPassword.getValue())) {
			ConfPassword.setComponentError(new UserError("Passwords Do Not Match."));
		}
		if(Password.isEmpty()) {
			Password.setComponentError(new UserError("Please Enter Your Password"));
		}
		if(ConfPassword.isEmpty()) {
			ConfPassword.setComponentError(new UserError("Please Confirm Your Password"));
		}
		
	}
public Register() {

	Panel panel=new Panel();
	panel.setHeight("600px");
	panel.setWidthUndefined();
	addComponent(panel);
	setComponentAlignment(panel,Alignment.MIDDLE_CENTER);
	
	FormLayout content=new FormLayout();
	content.addStyleName("Template");
	content.setMargin(true);
	
  	Label test=new Label("<p style = \"font-family:georgia,garamond,serif;font-size:30px;\">\r\n" + 
  	  		"       <b><u>Register</u></b> " + 
  	  		"      </p>" ,ContentMode.HTML);
  	 content.addComponent(test);	
 	content.setComponentAlignment(test,Alignment.TOP_CENTER);

  	 
	     Name=new TextField();
		  Name.setCaption("Name");
		  Name.setWidth("330px");
		  Name.setPlaceholder("Name");
		  content.addComponent(Name);
		 	content.setComponentAlignment(Name,Alignment.MIDDLE_CENTER);

		  
		  

		   StudentNumber=new TextField();
		  StudentNumber.setCaption("Student Number");
		  StudentNumber.setWidth("330px");
		  StudentNumber.setPlaceholder("Student Number");
		  content.addComponent(StudentNumber);
		 	content.setComponentAlignment(StudentNumber,Alignment.MIDDLE_CENTER);

		  
		   Password=new PasswordField();
		  Password.setCaption("Password");
		  Password.setWidth("330px");
		  Password.setPlaceholder("Password");
		  content.addComponent(Password);
		 	content.setComponentAlignment(Password,Alignment.MIDDLE_CENTER);

		  
		   ConfPassword=new PasswordField();
		  ConfPassword.setCaption("Confirm Password");
		  ConfPassword.setWidth("330px");
		  ConfPassword.setPlaceholder("Confirm Password");
		 content.addComponent(ConfPassword);
		 	content.setComponentAlignment(ConfPassword,Alignment.MIDDLE_CENTER);

		  
  	  CheckBox PMA=new CheckBox("Are you on Postgraduate Merit Award(PMA)?",false);
	  PMA.addValueChangeListener(event ->
	    PMA.setValue(! PMA.getValue()));
	 content.addComponent(PMA);
	 content.setComponentAlignment(PMA, Alignment.BOTTOM_LEFT);
	 
														//FILE UPLOAD!!!!!						
	// Initialize a list with items
	 List<String> list = new ArrayList<String>();
	 String dummy="COMS1016 (Discrete Computational Structures)";
	 list.add(dummy);
	 list.add("COMS2002 (Database Fundementals)");
	 list.add("COMS2013 (Mobile Computing)");
	 list.add("COMS2014 (Computer Networks)");
	 list.add("COMS2015 (Analysis of Algorithms)");
	 list.add("COMS3003 (Formal Languages and Automata)");	
	 list.add("COMS3005 (Advanced Analysis of Algorithms)");
	 list.add("COMS3009 (Software Design)");
	 list.add("COMS3010 (Operating Systems & System Programming)");	
	 list.add("COMS3007 (Machine Learning)");
	 list.add("COMS3006 (Computer Graphics & Vis.)");
	 list.add("COMS3008(Parallel Computing)");
	 list.add("COMS3011 (Software Design Project)");

	 // Initialize the ComboBoxMultiselect
	 final ComboBoxMultiselect<String> comboBoxMultiselect = new ComboBoxMultiselect<>();
	 comboBoxMultiselect.setPlaceholder("Courses");
	  comboBoxMultiselect.setWidth("430px");
	 comboBoxMultiselect.setCaption("Please Select The Courses You Would Like to Tutor(Max. 5");
	 comboBoxMultiselect.setItems(list);
	 content.addComponent(comboBoxMultiselect);
								panel.setContent(content);						
								 Button button = new Button("Register",
							        		event->Notification.show(comboBoxMultiselect.getValue().toString()));
								 
content.addComponent(button);
    }

  
}
