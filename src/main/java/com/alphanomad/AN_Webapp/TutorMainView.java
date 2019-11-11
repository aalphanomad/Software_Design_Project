package com.alphanomad.AN_Webapp;

import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.*;

public class TutorMainView extends VerticalLayout implements View
{
	Button pdf_button;
	 	ArrayList<String> courses;
	 	static JsonObject filtered;


	public TutorMainView()
	{

	}

	/*@Override
	public void enter(ViewChangeEvent vc_event)
	{
		
		removeAllComponents();
		DBHelper dbh = new DBHelper();
        
        DBHelper dbh1 = new DBHelper();
        String studentName = ((MyUI) getUI()).get_user_info().get_name();
        String studentNum = ((MyUI) getUI()).get_user_info().get_student_num();

		String ans = dbh1.php_request("get_ValidCourses", par, val);
		filtered = dbh.parse_json_string(ans);
		ans = filtered.get("result").getAsJsonArray().toString();
		
		
		//Notification.show(currPassword);
		JsonArray test=dbh.parse_json_string_arr(conf);
		the_password=test.getAsJsonArray().get(0).getAsJsonArray().get(0).toString();
		//System.out.println("hiiiii  " + the_password);
		the_password=the_password.substring(1, the_password.length()-1);
		System.out.println("byeeeeee  " + all.get(0) + " and " + all.get(1));
		ArrayList<String> month_strs = new ArrayList<String>();

		
	}*/

}
