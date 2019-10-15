package com.alphanomad.AN_Webapp;

import com.google.gson.JsonObject;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction.KeyCode;
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
public class LoginView extends VerticalLayout implements View
{
	public TextField Username;
	public PasswordField Password;
	public MyUI parent_ui;
	public JsonObject login_obj;

	public boolean TheLogin(String student_num, String password)
	{

		Username.setComponentError(null);
		Password.setComponentError(null);

		String[] params = { "student_num", "password" };
		String[] values = { student_num, password };
		DBHelper dbh = new DBHelper();
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
			String ans = dbh.php_request("signin", params, values);

			login_obj = dbh.parse_json_string(ans);

			if (login_obj.get("result").getAsString().equals("1"))
			{

				((MyUI) getUI()).set_user_info(new UserInfo(login_obj.get("name").getAsString(),
						login_obj.get("student_num").getAsString(), login_obj.get("role").getAsString()));
				
				((MyUI)getUI()).logged_in = true;
				// ((MyUI) UI.getCurrent()).set_user_info( new
				// UserInfo(login_obj.get("name").getAsString(),
				// login_obj.get("student_num").getAsString(),
				// login_obj.get("role").getAsString()));
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



	public void handle_login(String username, String password)
	{


	}

}
