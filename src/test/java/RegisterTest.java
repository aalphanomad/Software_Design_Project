//package com.alphanomad.AN_Webapp;
import com.alphanomad.AN_Webapp.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class RegisterTest {
	
	MyUI ui;
	@Before
	public void setup() throws Exception {
		//For MyUI.java
		ui=new MyUI();
		
		
	}

	@Test
	public void test() {
		Register register=new Register();
		
		TextField Name;
		Name=new TextField();
		Name.setIcon(VaadinIcons.USER);
		Name.setCaption("Name"); 
		Name.setPlaceholder("Name");
	  	  
	  	TextField StudentNumber;
	  	StudentNumber=new TextField();
	  	StudentNumber.setIcon(VaadinIcons.USER);
	  	StudentNumber.setCaption("Username"); 
	  	StudentNumber.setPlaceholder("Username");
	  	
	  	PasswordField Password;
		 Password=new PasswordField();
	  	  Password.setCaption("Password");
	  	  Password.setIcon(VaadinIcons.PASSWORD);
	  	  Password.setPlaceholder("Password");
	  	  
	  	PasswordField ConfPassword;
	  	ConfPassword=new PasswordField();
	  	ConfPassword.setCaption("Password");
	  	ConfPassword.setIcon(VaadinIcons.PASSWORD);
	  	ConfPassword.setPlaceholder("Password");
	  	
	  	register.ConfPassword=ConfPassword;
	  	register.Name=Name;
	  	register.StudentNumber=StudentNumber;
	  	register.Password=Password;
	  	
	  	register.TheRegister();
	}
	
	@After
	public void tearDown() throws Exception{
		//For MyUI.java
		//register.
		
		//For MainView.java
		
	}

}
