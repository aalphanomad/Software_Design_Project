package com.alphanomad.AN_Webapp;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.*;

public class TutorMainView extends VerticalLayout implements View
{
	ArrayList<String> courses;
	static JsonObject filtered;

	public TutorMainView()
	{

	}
	
	//function below is used to get the courses confirmed for a tutor to tutor in
	public static ArrayList<String> GetCourses(String FromDB)
	{

		// Notification.show(ans);
		ArrayList<String> new_courses = new ArrayList<String>();

		String[] courses1 = FromDB.split(",");
		for (int i = 0; i < courses1.length; i++)
		{
			//System.out.println("hahaha  " + courses1[i]);
			
			String[] courses2 = courses1[i].split(":");
			
			//System.out.println("lalala  " + courses2[0] + " and " + courses2[1]);
			if (i == courses1.length - 1)
			{
				if (!courses2[1].substring(0, courses2[1].length() - 2).equals("null"))
				{
					new_courses.add(courses2[1].substring(1, courses2[1].length() - 3));

				}
			} else
			{
				if (!courses2[1].equals("null"))
					new_courses.add(courses2[1].substring(1, courses2[1].length() - 1));
			}
		}
		return new_courses;
	}

	@Override
	public void enter(ViewChangeEvent vc_event)
	{
		// THIS PREVENTS USERS FROM PRESSING BACK TO LOGIN WITHOUT A PASSWORD
		if (! ((MyUI)getUI()).logged_in)
		{
			getUI().getNavigator().navigateTo("login");
		} 
		
		removeAllComponents();
		DBHelper dbh = new DBHelper();

		// DEFINE COMPONENTS HERE
		
		//setting title for navigation bar and theme
		Label title = new Label("Menu");
        title.addStyleName(ValoTheme.MENU_TITLE);

        Button profile_button = new Button("Profile", event -> getUI().getNavigator().navigateTo("profile"));
        //profile_button.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);
        
        
        DBHelper dbh1 = new DBHelper();
        String studentName = ((MyUI) getUI()).get_user_info().get_name();
        String studentNum = ((MyUI) getUI()).get_user_info().get_student_num();
        
        //the code below, gets the courses confirmed for a tutor
        String[] par = { "name", "student_num" };
		String[] val = { studentName, studentNum };

		String ans = dbh1.php_request("get_ValidCourses", par, val);
		filtered = dbh.parse_json_string(ans);
		ans = filtered.get("result").getAsJsonArray().toString();
		
		//if no courses are confirmed then the arraylist of courses is empty
		if(ans.equals("[]")) {
			courses = null;
		}
		//else we fill the arraylist with the relevant courses 
		else {
			courses = GetCourses(ans);
			for(int i = 0; i<courses.size(); i++) {
				System.out.println("okokokok  " + courses.get(i));
			}
		}
		
		
		
		/*for(int i = 3; i<conf.length(); i = i+6) {
			all.add(conf.charAt(i));
		}
		
		for(int i = 0; i<all.size(); i++) {
			if(all.get(i).equals('1')) {
				count++;
			}
		}
		
		//Notification.show(currPassword);
		JsonArray test=dbh.parse_json_string_arr(conf);
		the_password=test.getAsJsonArray().get(0).getAsJsonArray().get(0).toString();
		//System.out.println("hiiiii  " + the_password);
		the_password=the_password.substring(1, the_password.length()-1);
		System.out.println("byeeeeee  " + all.get(0) + " and " + all.get(1));*/
        
        
		ArrayList<String> month_strs = new ArrayList<String>();
		month_strs.add("Jan");
		month_strs.add("Feb");
		month_strs.add("Mar");
		month_strs.add("Apr");
		month_strs.add("May");
		month_strs.add("Jun");
		month_strs.add("Jul");
		month_strs.add("Aug");
		month_strs.add("Sep");
		month_strs.add("Oct");
		month_strs.add("Nov");
		month_strs.add("Dec");
		ComboBox<String> months = new ComboBox<String>();
		months.setItems(month_strs);
		months.setDescription("Choose month to generate claim form for. Leave empty to get full claim history");
		//months.setCaption("Choose month to generate claim form for. Leave Blank to generate full record");
		months.setEmptySelectionCaption("Choose month...");
        
   
        
        Button pdf_button = new Button("Generate Claims Form", event -> 
		{
			if(months.getSelectedItem() != null)
			{
				String[] parameters = { "table", "target", "student_num", "month" };
				String[] valuess = { "BOOKINGS", "*", studentNum, months.getSelectedItem().get()};
				UI.getCurrent().getPage().open(dbh.php_request("select_booking", parameters, valuess), "_blank");
			}
			else
			{
				String[] parameters = { "table", "target", "student_num"};
				String[] valuess = { "BOOKINGS", "*", studentNum};
				UI.getCurrent().getPage().open(dbh.php_request("select_booking", parameters, valuess), "_blank");
			}
		});
        
        
		Button Login = new Button("Logout", event -> 
		 {
			 getUI().getNavigator().navigateTo("login");
			 ((MyUI)getUI()).logged_in = false;
			
		});
		// Button Register=new
		// Button("Register",event->getUI().getNavigator().navigateTo("register"));

		Button History = new Button("History", event -> getUI().getNavigator().navigateTo("history"));

		//the claim form button below is only accessible to tutors with confirmed courses
		Button claimForm_button = new Button("New Claim", event -> {
			if(courses==null) {
				Notification.show("You do not have access");
			}
			else {
				getUI().getNavigator().navigateTo("claimform");
			}
		});
		//add the following buttons to the navigation bar
        CssLayout menu = new CssLayout(title, claimForm_button, profile_button, History, Login);
        menu.addStyleName(ValoTheme.MENU_ROOT);	//apply certain theme to bar

        //certain css layout to apply to navigation bar
        CssLayout viewContainer = new CssLayout();

        //adding the navigation bar to the page
        HorizontalLayout mainLayout = new HorizontalLayout(menu, viewContainer);
        mainLayout.setSizeFull();
        addComponent(mainLayout);

		

		// ADD COMPONENTS TO UI HERE

		HorizontalLayout pdf_row = new HorizontalLayout();
		pdf_row.addComponent(pdf_button);
		pdf_row.addComponent(months);
		addComponent(pdf_row);
		// addComponent(Register);
		
	}

}
