package com.alphanomad.AN_Webapp;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

public class LoginViewTest {

	MyUI ui;
	@Before
	public void setup() throws Exception {
		
		ui=new MyUI();
		
	}
	

	@Test
	public void test() {
		String name="Marubini";
		String studNum="1622535";
		String role="student";
		//ui.TheLogin(studNum, studNum);

		LoginView loginView=new LoginView(ui);
		
		loginView.parent_ui=ui;
		
		TextField Username;
		 Username=new TextField();
	  	  Username.setIcon(VaadinIcons.USER);
	  	  Username.setCaption("Username"); 
	  	  Username.setPlaceholder("Username");
	  	  
		PasswordField Password;
		 Password=new PasswordField();
	  	  Password.setCaption("Password");
	  	  Password.setIcon(VaadinIcons.PASSWORD);
	  	  Password.setPlaceholder("Password");
	  	 
		loginView.Password=Password;
		loginView.Username=Username;
		loginView.TheLogin(studNum, studNum);
		//loginView.enter(ui);
		
		//loginView.handle_login(name, studNum);
		
	}
	
	@After
	public void tearDown() throws Exception{
		//For MyUI.java
		//ui.close();
		
		//For MainView.java
		
	}

}
