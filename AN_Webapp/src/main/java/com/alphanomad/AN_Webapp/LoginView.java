package com.alphanomad.AN_Webapp;

import com.google.gson.JsonObject;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
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
	MyUI parent_ui;
	JsonObject login_obj;
	
	  public boolean TheLogin(String student_num,String password) {
		
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
				return false;
	
			}
			else {
		    String ans=dbh.php_request("signin", params, values);
		   
		    login_obj = dbh.parse_json_string(ans);
		   
		   if(login_obj.get("result").getAsString().equals("1")) {
			  
			   ((MyUI) getUI()).set_user_info( new UserInfo(login_obj.get("name").getAsString(), login_obj.get("student_num").getAsString(), login_obj.get("role").getAsString()));
			   //((MyUI) UI.getCurrent()).set_user_info( new UserInfo(login_obj.get("name").getAsString(), login_obj.get("student_num").getAsString(), login_obj.get("role").getAsString()));
			   return true;
		   }else {
			   //Password.setComponentError(new UserError("Incorrect Username/Password. Please Try Again."));
			   return false;
			  
		   }
			}
			
	  }
  public  LoginView(MyUI parent_ui) {
	  
	  	 
  }
  
  @Override
  public void enter(ViewChangeEvent vc_event)
  {
	  //System.out.println(vc_event.getParameters());
	  removeAllComponents();
	  ((MyUI)getUI()).set_user_info(new UserInfo("", "", ""));
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
				event -> handle_login(Username.getValue(), Password.getValue()));
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
  
  
  private void handle_login(String username, String password)
  {
	  if(TheLogin(username, password))
	  {
		 if(login_obj.get("role").getAsString().equals("0")) {
		  getUI().getNavigator().navigateTo("tutormain");
		 }
		 else {
			 getUI().getNavigator().navigateTo("lectmain");

		 }
		 
	  }
	  else
	  {
		  Password.setComponentError(new UserError("Incorrect Username/Password. Please Try Again."));
		  Notification.show("Login Failed");
	  }
	  
  }


	  	
	    
	  
  }
	 



  