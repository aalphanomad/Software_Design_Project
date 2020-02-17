package com.alphanomad.AN_Webapp;

import java.io.File;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.google.gson.JsonObject;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.FileResource;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@Theme("mytheme")
@StyleSheet({"https://fonts.googleapis.com/css?family=Orbitron"})
public class LoginView extends VerticalLayout implements View
{
	//Declares the Components that need to be accessed globally
	public TextField Username;
	public PasswordField Password;
	public MyUI parent_ui;
	public JsonObject login_obj;
	
	
	 public static String get_SHA_1_SecurePassword(String passwordToHash,String Student_num)
	    {
	        String generatedPassword = null;
	        try {
	        	/*
	        	String temp_salt="";
	        	for(int i=0;i<Student_num.length();i++) {
	        		if(Character.isDigit(Student_num.charAt(i))) {
	        			temp_salt=temp_salt+Student_num.charAt(i);
	        		}
	        	}
	        	*/
	        	//System.out.println("TEST1"+temp_salt);
	        	//Byte salt=Byte.valueOf(temp_salt);
	        	byte[] salt= Student_num.getBytes();

	            MessageDigest md = MessageDigest.getInstance("SHA-1");
	            md.update(salt);
	            byte[] bytes = md.digest(passwordToHash.getBytes());
	            StringBuilder sb = new StringBuilder();
	            for(int i=0; i< bytes.length ;i++)
	            {
	                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	            }
	            generatedPassword = sb.toString();
	            System.out.println(generatedPassword);
	        } 
	        catch (NoSuchAlgorithmException e) 
	        {
	            e.printStackTrace();
	        }
	        return generatedPassword;
	    }

	public boolean TheLogin(String student_num, String password)
	{
		get_SHA_1_SecurePassword(password,student_num);
		
		
		//Ensures that checking for errors is refreshed everytime we attempt to login
		Username.setComponentError(null);
		Password.setComponentError(null);

		String[] params = { "student_num", "password" };
		String[] values = { student_num, get_SHA_1_SecurePassword(password,student_num)};
		DBHelper dbh = new DBHelper();
		//Below,  we perform validation to ensure that the necessary data is entered 
		
		if (Username.isEmpty())
		{
			Username.setComponentError(new UserError("Please Enter your Username"));
		}
		if (Password.isEmpty())
		{
			Password.setComponentError(new UserError("Please Enter your Password"));
			return false;

		} else
		{
			//We attempt to login here
			String ans = dbh.php_request("signin", params, values);
			
			login_obj = dbh.parse_json_string(ans);
			System.out.println("ANS"+login_obj.get("result").getAsString());
			//If the login is successful, we set the details of the newly logged in user and proceed to the relavant home screen
			
			if (login_obj.get("result").getAsString().equals("1"))
			{

				((MyUI) getUI()).set_user_info(new UserInfo(login_obj.get("name").getAsString(),
						login_obj.get("student_num").getAsString(), login_obj.get("role").getAsString()));
				
				((MyUI)getUI()).logged_in = true;
				
				return true;
			} else
			{
				// Password.setComponentError(new UserError("Incorrect Username/Password. Please
				// Try Again."));
				return false;

			}
		}

	}

	public LoginView(MyUI parent_ui)
	{

	}

	@Override
	public void enter(ViewChangeEvent vc_event)
	{
		removeAllComponents();
		AbsoluteLayout layout=new AbsoluteLayout();
		
		((MyUI)getUI()).logged_in = false;
		((MyUI) getUI()).set_user_info(new UserInfo("", "", ""));
		setSizeFull();
		addStyleName("image-backgound");
		
		// Find the application directory
		String basepath = VaadinService.getCurrent()
		                  .getBaseDirectory().getAbsolutePath();

		// Image as a file resource
		FileResource resource = new FileResource(new File("src/main/webapp/WEB_INF/images/image.png"));

		// Show the image in the application
		Image image = new Image("Image from file", resource);
		image.setWidth("140px");
		image.setHeight("140px");
		layout.addComponent(image,"left:658px;top:0px");
		
		Label AlphaNomad = new Label("<p\r\n"+ "<b><u>AlphaNomad</u></b> " + "</p>", ContentMode.HTML);
		layout.addComponent(AlphaNomad,"left:530px ; top:80px");
		AlphaNomad.setStyleName("fancy"); 
		addComponent(layout);
		//setComponentAlignment(AlphaNomad, Alignment.TOP_CENTER);  

		
		//The panel is where all the useful components such as the TextFields and Buttons will be added in order to improve the appearance
		Panel panel = new Panel();
		//panel.setHeight("300px");
		panel.setWidthUndefined();
		layout.addComponent(panel,"left:518px;top:220px");


	

		//Adds a login label
		FormLayout content = new FormLayout();
		content.addStyleName("Template");
		content.setMargin(true); 
		Label test = new Label("<p style = \"font-family:georgia,garamond,serif;font-size:30px;\">\r\n"
				+ "       <b><u>Login</u></b> " + "      </p>", ContentMode.HTML);
		content.addComponent(test);



		//Creates the field for the user to enter their student number
		Username = new TextField();
		Username.setIcon(VaadinIcons.USER);
		Username.setCaption("Username");
		Username.setPlaceholder("Username");
		content.addComponent(Username);
		Username.focus();
		panel.setContent(content);


		//Creates a field for the user to enter to enter their password
		Password = new PasswordField();
		Password.setCaption("Password");
		Password.setIcon(VaadinIcons.PASSWORD);
		Password.setPlaceholder("Password");
		content.addComponent(Password);

		HorizontalLayout buttons = new HorizontalLayout();

		//Creates the login and register button
		Button button1 = new Button("Login", event -> handle_login(Username.getValue(), Password.getValue()));
		button1.setClickShortcut(KeyCode.ENTER);
		Button button2 = new Button("Register", event -> getUI().getNavigator().navigateTo("register"));
		//Below aligns the components o the panel
		
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

	//This function ensures that the user is navigated to the correct home screen based on their role
	public void handle_login(String username, String password)
	{
		if (TheLogin(username, password))
		{
			if (login_obj.get("role").getAsString().equals("0"))
			{
				getUI().getNavigator().navigateTo("tutormain");
			} else if (login_obj.get("role").getAsString().equals("1"))
			{
				getUI().getNavigator().navigateTo("lectmain");

			} else if (login_obj.get("role").getAsString().equals("2")
					|| login_obj.get("role").getAsString().equals("4"))
			{
				getUI().getNavigator().navigateTo("adminmain");

			}
			
			else if (login_obj.get("role").getAsString().equals("3"))
			{
				getUI().getNavigator().navigateTo("chooserole");

			}

		} else
		{
			Password.setComponentError(new UserError("Incorrect Username/Password. Please Try Again."));
			Notification.show("Login Failed");
		}

	
	
	}
	


}
