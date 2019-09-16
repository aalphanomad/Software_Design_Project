package com.alphanomad.AN_Webapp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.vaadin.addons.ComboBoxMultiselect;

import com.google.gson.JsonObject;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


/**
 * This view is the view which shows the profile
 * it shows the users name and email and all subjects they are linked to
 */
public class ProfileView extends VerticalLayout implements View{

    private UserInfo user;


	public ProfileView(MyUI ui) {
    	
    }
    
 

    
	/**
	 * Make a profile view for a specific user
	 * @param ui Provided by vaadin
	 * @param user_info a UserInfo object for the user you want to view
	 */
	public ProfileView(MyUI ui ,UserInfo user_info) {
		this.user = user_info;
	}

    
    
    @Override
    public void enter(ViewChangeEvent event)
    {
    	this.removeAllComponents();
    	
    	System.out.println("parameters");
    	System.out.println(event.getParameters());
    	System.out.println(event.getOldView().toString());
  	  
    	String stud_num = "";
    	MyUI ui = (MyUI) getUI();
    	
    	// if no user was provided in the constructor then use the currently logged in user
    	if(user == null)
    	{
    		user = ui.get_user_info(); 	
    	}
    	
    	stud_num = user.get_student_num();
    	
    	DBHelper dbh = new DBHelper();
    	
    	
    	String[] params = {"student_num"} ;
    	String[] values = {stud_num};
    	String name = dbh.php_request("get_name", params, values); 
    	
    	params = new String[] {"student_num","name"} ;
    	values = new String[] {stud_num,name};
    	String courses = dbh.php_request("get_courses", params, values);
    	JsonObject courses_obj = dbh.parse_json_string(courses);
    	
    	Button home_button = new Button("go to main view",
	            btn_event -> getUI().getNavigator().navigateTo("main"));
    	
    	addComponent(make_user_info_panel(name, stud_num));
    	addComponent(make_courses_panel(courses_obj));
    	addComponent(home_button);
    }
    
    
   
    /**
     * simple function to make a view that shows the users email and name
     * @param name
     * @param student_number
     * @return a Vertical layout to be added to a page
     */
    public Panel make_user_info_panel(String name, String student_number)
    {
    	Panel panel = new Panel();
    	VerticalLayout inner = new VerticalLayout();
    	
    	ExternalResource resource = new ExternalResource ("https://ae01.alicdn.com/kf/HTB1w.uMacfrK1RkSnb4q6xHRFXay/Pet-Glasses-Dog-Glasses-Cute-cat-toy-for-Little-Dog-Eye-Wear-Dog-Sunglasses-Photos-Props.jpg");
    	Image image = new Image("Profile Image");
    	
    	image.setSource(resource);
    	image.setHeight("100px");
    	
    	inner.addComponent(image);
    	
    	VerticalLayout details = new VerticalLayout();
    	
    	HorizontalLayout stud_num_line = new HorizontalLayout();
    	stud_num_line.addComponent(new Label("Student Number:"));
    	stud_num_line.addComponent(new Label(student_number));
    	
    	HorizontalLayout email_line = new HorizontalLayout();
    	email_line.addComponent(new Label("Email Address:"));
    	email_line.addComponent(new Label(student_number+"@students.wits.ac.za"));
    	
    	inner.addComponent(stud_num_line);
    	inner.addComponent(email_line);
    	
    	panel.setCaption("User Information");
    	panel.setContent(inner);
    	return panel;
    }
    
    /**
     * 
     * @param courses a Json Object
     * @return a vertical layout containing a collapseable list of courses that the user is linked to
     */
    public Panel make_courses_panel(JsonObject courses)
    {
    	Panel panel = new Panel();
    	VerticalLayout courses_inner = new VerticalLayout();
    	try
    	{
    		JsonObject data = courses.getAsJsonArray("result").get(0).getAsJsonObject();
        	
        	
        	
        	for(String j : data.keySet())
        	{
        		try
        		{
        			courses_inner.addComponent(new Label (data.get(j).getAsString()+"\n"));
        		}
        		catch(UnsupportedOperationException e)
        		{
        			//courses_inner.addComponent(new Label("probs null value"));
        		}
        		
        	}
    	}
    	catch (Exception e) {
			// TODO: handle exception
		}
    	
    	
    	panel.setCaption("Courses");
    	panel.setContent(courses_inner);
    	
    	
    	return panel;
    }
    
    public boolean handle_course_change(ArrayList<String> courses, String stud_num)
    {
    	boolean result = true;
    	for(String course: courses)
    	{
    		update_courses(stud_num, course, "");
    	}
    	
    	return result;
    }
    
    
    /**
     * 
     * @param stud_num
     * @param course
     * @param confirmed
     * @return
     */
    public boolean update_courses(String stud_num, String course, String confirmed)
    {
    	boolean result = false;
    	DBHelper dbh = new DBHelper();
    	String value;
    	
    	
    	if(confirmed.length() > 0)
    	{
    		String[] params = {"student_num","course","confirmed"} ;
    		String[] values = {stud_num,course,confirmed};
    		value = dbh.php_request("update_courses", params, values); 
    	}
    	else
    	{
    		String[] params = {"student_num","course"} ;
    		String[] values = {stud_num,course};
    		value = dbh.php_request("update_courses", params, values); 
    	}
    	
    	result = Boolean.parseBoolean(value);
    	
    	return result;
    }
}
