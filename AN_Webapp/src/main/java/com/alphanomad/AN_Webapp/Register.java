package com.alphanomad.AN_Webapp;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class Register extends VerticalLayout implements View{
public Register() {
/*
        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener(e -> {
            layout.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it works!"));
        });
        */
        
        //MAKE BIGGER
	    addComponent(new Label("Register"));
	    
	    TextField Name=new TextField();
		  Name.setCaption("Name");
		  Name.setPlaceholder("Name");
		  addComponent(Name);
		  
		  

		  TextField StudentNumber=new TextField();
		  StudentNumber.setCaption("Student Number");
		  StudentNumber.setPlaceholder("Student Number");
		  addComponent(StudentNumber);
		  
		  PasswordField Password=new PasswordField();
		  Password.setCaption("Password");
		  Password.setPlaceholder("Password");
		  addComponent(Password);
		  
		  PasswordField ConfPassword=new PasswordField();
		  ConfPassword.setCaption("Confirm Password");
		  ConfPassword.setPlaceholder("Confirm Password");
		 addComponent(ConfPassword);
		  
  	  CheckBox PMA=new CheckBox("Are you on Postgraduate Merit Award(PMA)?",false);
	  PMA.addValueChangeListener(event ->
	    PMA.setValue(! PMA.getValue()));
	 addComponent(PMA);
	  
	  CheckBoxGroup<String> multi1 =
			new CheckBoxGroup<>("Please Select The Courses You Would Like to Tutor( Max. 5)");
			multi1.setItems("COMS1015(Basic Computer Organisation)", "COMS1018(Introduction to Data Structures and Algorithms)", "COMS1016(Discrete Computational Structures)");
			multi1.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
			addComponent(multi1);
			
			  CheckBoxGroup<String> multi2 =
						new CheckBoxGroup<>();
						multi2.setItems("COMS1016(Discrete Computational Structures)","COMS2002(Database Fundementals)","COMS2013(Mobile Computing)");
						multi2.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
						addComponent(multi2);
						
						  CheckBoxGroup<String> multi3 =
									new CheckBoxGroup<>();
									multi3.setItems("COMS2014(Computer Networks)","COMS2015(Analysis of Algorithms)","COMS3003(Formal Languages and Automata)");
									multi3.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
									addComponent(multi3);
									
									  CheckBoxGroup<String> multi4 =
												new CheckBoxGroup<>();
												multi4.setItems("	COMS3005(Advanced Analysis of Algorithms)","COMS3009(Software Design)","COMS3010(Operating Systems & System Programming)");
												multi4.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
												addComponent(multi4);
												
												CheckBoxGroup<String> multi5 =
														new CheckBoxGroup<>();
														multi5.setItems("	COMS3007(Machine Learning)","COMS3006(Computer Graphics & Vis.)","COMS3008(Parallel Computing)","COMS3011(Software Design Project)");
														multi5.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
														addComponent(multi5);
			
														//FILE UPLOAD!!!!!
														
														
												        Button button = new Button("Register");
												        button.addClickListener(e -> {
												           addComponent(new Label("Thanks " + Name.getValue() 
												                    + ", it works!"));
												        });
												      addComponent(button);
														
														
    }

  
}
