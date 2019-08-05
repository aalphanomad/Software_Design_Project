package com.alphanomad.AN_Webapp;

import com.google.gson.JsonObject;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;


/**
 * This view is the view which shows the profile
 * it shows the users name and email and all subjects they are linked to
 */
public class ProfileView extends VerticalLayout implements View{

    public ProfileView() {
    	//TODO: Replace this with actual data
    	String stud_num = "1";
    	
    	DBHelper dbh = new DBHelper();
    	
    	
    	String[] params = {"student_num"} ;
    	String[] values = {stud_num};
    	String name = dbh.php_request("get_name", params, values); 
    	
    	params = new String[] {"student_num","name"} ;
    	values = new String[] {stud_num,name};
    	String courses = dbh.php_request("get_courses", params, values);
    	JsonObject courses_obj = dbh.parse_json_string(courses);
    	
    	Button home_button = new Button("go to main view",
	            event -> getUI().getNavigator().navigateTo("main"));
    	
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
    private Panel make_user_info_panel(String name, String student_number)
    {
    	Panel panel = new Panel();
    	VerticalLayout inner = new VerticalLayout();
    	
    	
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
    private Panel make_courses_panel(JsonObject courses)
    {
    	Panel panel = new Panel();
    	
    	JsonObject data = courses.getAsJsonArray("result").get(0).getAsJsonObject();
    	
    	VerticalLayout courses_inner = new VerticalLayout();
    	
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
    	
    	panel.setCaption("Courses");
    	panel.setContent(courses_inner);
    	
    	
    	return panel;
    }
}
