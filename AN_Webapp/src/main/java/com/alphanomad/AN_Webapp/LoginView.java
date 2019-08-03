package com.alphanomad.AN_Webapp;

import com.vaadin.annotations.StyleSheet;
import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@Theme("mytheme")
public class LoginView extends VerticalLayout implements View {
	TextField Username;
	PasswordField Password;
	  public String TheLogin(String student_num,String password) {
		
		  Username.setComponentError(null);
		  Password.setComponentError(null);
		  
		  String[] params = {"student_num","password"} ;
		  String[] values= {student_num,password};
			DBHelper dbh = new DBHelper();
			if(Username.isEmpty() ) {
				Username.setComponentError(new UserError("Please Enter your Username"));
			}
			if(Password.isEmpty()) {
				Password.setComponentError(new UserError("Please Enter your Password"));
				return "Fail";
	
			}
			else {
		   String ans=dbh.php_request("signin", params, values);
		   if(ans.length()>14) {
			   return "Pass";
		   }else {
			   return "Fail";
			  // Password.setComponentError(new UserError("Incorrect Username/Password. Please Try Again."));
		   }
			}
			
	  }
  public  LoginView() {
	  
		String[] values = {"1","test"};
		DBHelper dbh = new DBHelper();

 
	  setSizeFull();
	addStyleName("image-backgound");
	  	Panel panel=new Panel();
	  	panel.setHeight("500px");
	  	panel.setWidthUndefined();
	  	addComponent(panel);
	  	setComponentAlignment(panel,Alignment.MIDDLE_CENTER);
	  	
	  	FormLayout content=new FormLayout();
	  	content.addStyleName("Template");
	  	content.setMargin(true);
	  	Label test=new Label("<p style = \"font-family:georgia,garamond,serif;font-size:30px;\">\r\n" + 
	  	  		"       <b><u>Login</u></b> " + 
	  	  		"      </p>" ,ContentMode.HTML);
	  	 content.addComponent(test);
	  	 
	  	  
	  	   Username=new TextField();
	  	  Username.setIcon(VaadinIcons.USER);
	  	  Username.setCaption("Username"); 
	  	  Username.setPlaceholder("Username");
	  	content.addComponent(Username);
	    
	  	  
	  	   Password=new PasswordField();
	  	  Password.setCaption("Password");
	  	  Password.setIcon(VaadinIcons.PASSWORD);
	  	  Password.setPlaceholder("Password");
	  	 content.addComponent(Password);
	  	  
	  	 HorizontalLayout buttons=new HorizontalLayout();
	  	
	  	  Button button1 = new Button("Login",
				event -> Notification.show(TheLogin(Username.getValue(),Password.getValue())));
	  	 Button button2=new Button("Register",
	  			 event ->getUI().getNavigator().navigateTo("register"));
	  	 buttons.addComponent(button1);
	  	 buttons.addComponent(button2);
	  	 content.addComponent(buttons);
		 buttons.setComponentAlignment(button1, Alignment.BOTTOM_LEFT);
	  	 buttons.setComponentAlignment(button2, Alignment.BOTTOM_RIGHT);
	  	 content.setComponentAlignment(test, Alignment.MIDDLE_CENTER);
	  	 content.setComponentAlignment(Username, Alignment.MIDDLE_CENTER);
	  	 content.setComponentAlignment(Password, Alignment.MIDDLE_CENTER);
	  	 content.setComponentAlignment(buttons, Alignment.BOTTOM_LEFT);
	  	 
	  	 panel.setContent(content);
	  	 

  }

	  	
	    
	  
  }
	 



  