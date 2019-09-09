package com.alphanomad.AN_Webapp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.vaadin.addons.ComboBoxMultiselect;

import com.google.gson.JsonObject;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


/**
 * This view is the view which shows the profile
 * it shows the users name and email and all subjects they are linked to
 */
public class ProfileView extends VerticalLayout implements View{

    public ProfileView(MyUI ui) {
    	
    }
    
    @Override
    public void enter(ViewChangeEvent event)
    {
    	this.removeAllComponents();
    	//TODO: Replace this with actual data
    	String stud_num = "";
    	MyUI ui = (MyUI) getUI();
    	ui.get_user_info();
    	stud_num = ui.get_user_info().get_student_num();

    	
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
    private Panel make_user_info_panel(String name, String student_number)
    {
    	Panel panel = new Panel();
    	HorizontalLayout inner = new HorizontalLayout();
    	inner.setMargin(true);
    	
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
    	
    	details.addComponent(stud_num_line);
    	details.addComponent(email_line);
    	
    	inner.addComponent(details);
    	
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
    	
    	List < String > list = new ArrayList < String > ();
        list.add("COMS1016-(Discrete Computational Structures)");
        list.add("COMS2002-(Database Fundementals)");
        list.add("COMS2013-(Mobile Computing)");
        list.add("COMS2014-(Computer Networks)");
        list.add("COMS2015-(Analysis of Algorithms)");
        list.add("COMS3003-(Formal Languages and Automata)");
        list.add("COMS3005-(Advanced Analysis of Algorithms)");
        list.add("COMS3009-(Software Design)");
        list.add("COMS3010-(Operating Systems & System Programming)");
        list.add("COMS3007-(Machine Learning)");
        list.add("COMS3006-(Computer Graphics & Vis.)");
        list.add("COMS3008-Parallel Computing)");
        list.add("COMS3011-(Software Design Project)");

        ComboBoxMultiselect < String > course_combo_box = new ComboBoxMultiselect < > ();
        // Initialize the ComboBoxMultiselect
        course_combo_box.setPlaceholder("Courses");
        course_combo_box.setWidth("430px");
        course_combo_box.setCaption("Please Select The Courses You Would Like to Tutor(Max. 5");
        course_combo_box.setItems(list);
        course_combo_box.setVisible(false);
        
    	Button change_courses = new Button("Change courses",
	            event -> course_combo_box.setVisible(true));
    	
    	courses_inner.addComponent(change_courses);
    	
    	
    	panel.setCaption("Courses");
    	panel.setContent(courses_inner);
    	
    	
    	return panel;
    }
}
