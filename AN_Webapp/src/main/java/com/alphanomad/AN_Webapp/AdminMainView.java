package com.alphanomad.AN_Webapp;

import java.util.ArrayList;
import java.util.Set;

import org.apache.commons.lang.WordUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.UserError;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.ImageRenderer;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class AdminMainView extends VerticalLayout implements View
{

	public AdminMainView()
	{

	}

	@Override
	public void enter(ViewChangeEvent vc_event)
	{
		// THIS PREVENTS USERS FROM PRESSING BACK TO LOGIN WITHOUT A PASSWORD
		if (! ((MyUI)getUI()).logged_in)
		{
			getUI().getNavigator().navigateTo("login");
		} 
		
		//setting title for navigation bar and theme
		Label title = new Label("Menu");
        title.addStyleName(ValoTheme.MENU_TITLE);
		
		// empty the screen just in case
		components.clear();
		Button view_application_btn = new Button("view pending applications",
				btn_event -> getUI().getNavigator().navigateTo("coursealloc"));
		Button view_users_btn = new Button("view all users",
				btn_event -> getUI().getNavigator().navigateTo("tutorlist"));
		Button view_courses_btn = new Button("view all courses",
				btn_event -> getUI().getNavigator().navigateTo("courselist"));
		Button login = new Button("Logout", event -> 
		 {
			 getUI().getNavigator().navigateTo("login");	
		});
		
		UserInfo admin_info = ((MyUI) getUI()).get_user_info();
		
		String role=admin_info.get_role();
		
		//add the following buttons to the navigation bar
		CssLayout menu = new CssLayout(title, view_application_btn, view_users_btn, view_courses_btn, login);
        menu.addStyleName(ValoTheme.MENU_ROOT);

        //certain css layout to apply to navigation bar
        CssLayout viewContainer = new CssLayout();

        //adding the navigation bar to the page
        HorizontalLayout mainLayout = new HorizontalLayout(menu, viewContainer);
        mainLayout.setSizeFull();
        addComponent(mainLayout);
		
		if(role.equals("4")) {
			HorizontalLayout password_line = new HorizontalLayout();
			Button updatePassword = new Button("Change User's Password", 
					event1 -> {
						Panel p = new Panel();
						p.setHeight("200px");
						p.setWidthUndefined();
						
						password_line.addComponent(p);
						
						FormLayout fl = new FormLayout();
						fl.setMargin(true);
						
						
						TextField current = new TextField();
						current.setCaption("Enter Users' Student Number:");
						fl.addComponent(current);
						
						TextField new_password = new TextField();
						new_password.setCaption("Enter New Password:");
						fl.addComponent(new_password);
						
						TextField confirm_new = new TextField();
						confirm_new.setCaption("Confirm New Password:");
						fl.addComponent(confirm_new);
						
						PasswordField AdminPassword = new PasswordField();
						AdminPassword.setCaption("Enter Admin's Password:");
						fl.addComponent(AdminPassword);
						
						DBHelper dbh=new DBHelper();
						Button confirmPass = new Button("Confirm Password", 
								event2 -> {
									
									DBHelper dbh1=new DBHelper();
									String[] par = { "table", "target", "filter", "value" };
									String[] val = {"USER_INFORMATION","USER_PASSWORD","USER_PASSWORD",AdminPassword.getValue().toString()};
									
									String currPassword=dbh1.php_request("generic_select", par, val);
								
									JsonArray test=dbh.parse_json_string_arr(currPassword);
									String the_password=test.getAsJsonArray().get(0).getAsJsonArray().get(0).toString();
									the_password=the_password.substring(1, the_password.length()-1);
									
									String[] val2 = {"USER_INFORMATION","STUDENT_NUM","STUDENT_NUM",current.getValue().toString()};
									
									String userStudentNum=dbh1.php_request("generic_select", par, val2);
								
									JsonArray test2=dbh.parse_json_string_arr(userStudentNum);
									String theStudentNum=test2.getAsJsonArray().get(0).getAsJsonArray().get(0).toString();
									theStudentNum=theStudentNum.substring(1, theStudentNum.length()-1);
									
								    if(the_password.equals(AdminPassword.getValue().toString()) && new_password.getValue().toString().equals(confirm_new.getValue().toString())
								    		&& current.getValue().toString().equals(theStudentNum)) {
								    
										String[] params = {"password","student_num"};
										
								
										String[] values = {confirm_new.getValue().toString(), theStudentNum};
										dbh1.php_request("update_password", params, values);
										Notification.show("Password changed Successfully");
								    }else {
								    	Notification.show("Enter Correct Details");
								    }
									
						});
						
						fl.addComponent(confirmPass);
						p.setContent(fl);
			});
			
			password_line.addComponent(updatePassword);
			addComponent(password_line);
		}
		
		
	}

}
