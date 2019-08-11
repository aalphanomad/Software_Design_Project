package com.alphanomad.AN_Webapp;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class LoginView extends VerticalLayout implements View {


  public  LoginView() {
	  
  /*
    addLoginListener(evt -> {
      // Perform authentication check and update session here
      // Check the project source code for an example

      UI.getCurrent().navigate(MainView.class);
    });
    */

	  addComponent(new Label("Login"));
	  
	  TextField Username=new TextField();
	  Username.setCaption("Username");
	  Username.setPlaceholder("Username");
	  addComponent(Username);
	  
	  PasswordField Password=new PasswordField();
	  Password.setCaption("Password");
	  Password.setPlaceholder("Password");
	  addComponent(Password);
	  
	  Button button1 = new Button("Login",
	             event ->getUI().getNavigator().navigateTo("main"));
	    addComponent(button1);
	  /*
     Button button = new Button("Register",
             e -> UI.getCurrent().navigate(Register.class));
     add(button);
  */
  
  }
}
  