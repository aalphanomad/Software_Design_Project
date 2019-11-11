package com.alphanomad.AN_Webapp;

import java.io.File;

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

	public boolean TheLogin(String student_num, String password)
	{
		//Ensures that checking for errors is refreshed everytime we attempt to login
		Username.setComponentError(null);
		Password.setComponentError(null);

		String[] params = { "student_num", "password" };
		String[] values = { student_num, password };
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

			return true;
		}

	}

	public LoginView(MyUI parent_ui)
	{

	}

	/*@Override
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
	
	}*/
	


}
