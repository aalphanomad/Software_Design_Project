package com.alphanomad.AN_Webapp;

import java.util.ArrayList;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class AdminMainView extends VerticalLayout implements View{
	
	Set<CourseAllocObject> selected ;
	public AdminMainView() 
	{
		
    }
	
	@Override
	public void enter(ViewChangeEvent vc_event)
	{
		Button view_application_btn = new Button("view pending applications",
	            btn_event -> view_applications());
		addComponent(view_application_btn);
	}

	private void view_applications() 
	{
		// clear the screen
		for(Component c : this.components)
		{
			c.setVisible(false);
		}
		
		Grid<CourseAllocObject> g = new Grid<CourseAllocObject>();
		g.setSizeFull();
		g.setItems(get_unconfirmed_course_allocs());
		g.addColumn(CourseAllocObject::getStud_num).setCaption("Student Number");
		g.addColumn(CourseAllocObject::getCourse).setCaption("Course");
		g.addColumn(CourseAllocObject::getConfirmed).setCaption("Status");
		
		// switch to multiselect mode
		g.setSelectionMode(SelectionMode.MULTI);
		
		g.addSelectionListener(event -> {
		    selected = event.getAllSelectedItems();
		    Notification.show(selected.size() + " items selected");
		});
		addComponent(g);
		
		Button confirm_btn = new Button("Confirm/Deny selected applications",event -> {
			if(selected != null)
			{
				DBHelper dbh = new DBHelper();
				String[] params = {"student_num","course","confirmed"};
				
				
				for(CourseAllocObject cao : selected)
				{
					if (cao.getConfirmed().equals("0"))
					{
						String[] vals = {cao.getStud_num(),cao.getCourse(),"1"};
						dbh.php_request("update_courses", params, vals);
						g.setItems(get_unconfirmed_course_allocs());
					}
					else
					{
						String[] vals = {cao.getStud_num(),cao.getCourse(),"0"};
						dbh.php_request("update_courses", params, vals);
						g.setItems(get_confirmed_course_allocs());
					}
					
				}
			}
			else
			{
				Notification.show("No Items Selected");
			}
			
			
			
		}); 
		
		Button view_confirmed_btn = new Button("View Confirmed Applications",event -> {
			g.setItems(get_confirmed_course_allocs());
			
		}); 
		
		Button view_unconfirmed_btn = new Button("View Unconfirmed Applications",event -> {
			g.setItems(get_unconfirmed_course_allocs());
			
		}); 
		
		Button view_all_btn = new Button("View ALL Applications",event -> {
			g.setItems(get_all_course_allocs());
			
		}); 
		
		addComponent(confirm_btn);
		addComponent(view_confirmed_btn);
		addComponent(view_unconfirmed_btn);
		addComponent(view_all_btn);
		
		
	}
	
	ArrayList<CourseAllocObject> get_unconfirmed_course_allocs()
	{
		DBHelper dbh = new DBHelper();
		String[] parameters= { "table", "target","filter", "value" };
		String[] values = { "USER_COURSE_ALLOC", "*", "CONFIRMED","0" };
		
		String result = dbh.php_request("generic_select", parameters, values);
		System.out.println("result is :");
		System.out.println(result);
		JsonArray tutor_alloc_obj;
		try
		{
			tutor_alloc_obj = dbh.parse_json_string_arr(result);
		}
		catch (Exception e) {
			return new ArrayList<CourseAllocObject>();
		}
		
		ArrayList<CourseAllocObject> course_allocs = new ArrayList<CourseAllocObject>();

		
		try
    	{
			for(int x=0; x < tutor_alloc_obj.size(); x++)
			{
	    		JsonArray data = (JsonArray) tutor_alloc_obj.getAsJsonArray().get(x);
	        	try
	        	{
	        		
	        		CourseAllocObject cao = new CourseAllocObject(data.get(1).getAsString(), data.get(2).getAsString(), data.get(3).getAsString());
	        		course_allocs.add(cao);
	        	}
	        	catch(UnsupportedOperationException e)
	        	{
	        		System.out.println(e);
	        	}
			}
    	}
    	catch (Exception e) {
    		System.out.println(e);
		}
		return course_allocs;
	}
	
	ArrayList<CourseAllocObject> get_confirmed_course_allocs()
	{
		DBHelper dbh = new DBHelper();
		String[] parameters= { "table", "target","filter", "value" };
		String[] values = { "USER_COURSE_ALLOC", "*", "CONFIRMED","1" };
		
		String result = dbh.php_request("generic_select", parameters, values);
		System.out.println("result is :");
		System.out.println(result);
		JsonArray tutor_alloc_obj;
		try
		{
			tutor_alloc_obj = dbh.parse_json_string_arr(result);
		}
		catch (Exception e) {
			return new ArrayList<CourseAllocObject>();
		}
		
		ArrayList<CourseAllocObject> course_allocs = new ArrayList<CourseAllocObject>();

		
		try
    	{
			for(int x=0; x < tutor_alloc_obj.size(); x++)
			{
	    		JsonArray data = (JsonArray) tutor_alloc_obj.getAsJsonArray().get(x);
	        	try
	        	{
	        		System.out.println(data.get(1).getAsString());
	        		System.out.println(data.get(2).getAsString());
	        		System.out.println(data.get(3).getAsString());
	        		
	        		CourseAllocObject cao = new CourseAllocObject(data.get(1).getAsString(), data.get(2).getAsString(), data.get(3).getAsString());
	        		course_allocs.add(cao);
	        	}
	        	catch(UnsupportedOperationException e)
	        	{
	        		System.out.println(e);
	        	}
			}
    	}
    	catch (Exception e) {
    		System.out.println(e);
		}
		return course_allocs;
	}
	
	ArrayList<CourseAllocObject> get_all_course_allocs()
	{
		DBHelper dbh = new DBHelper();
		String[] parameters= { "table", "target","filter", "value" };
		String[] values = { "USER_COURSE_ALLOC", "*", "1","1" };
		
		String result = dbh.php_request("generic_select", parameters, values);
		System.out.println("result is :");
		System.out.println(result);
		JsonArray tutor_alloc_obj;
		try
		{
			tutor_alloc_obj = dbh.parse_json_string_arr(result);
		}
		catch (Exception e) {
			return new ArrayList<CourseAllocObject>();
		}
		
		ArrayList<CourseAllocObject> course_allocs = new ArrayList<CourseAllocObject>();

		
		try
    	{
			for(int x=0; x < tutor_alloc_obj.size(); x++)
			{
	    		JsonArray data = (JsonArray) tutor_alloc_obj.getAsJsonArray().get(x);
	        	try
	        	{
	        		System.out.println(data.get(1).getAsString());
	        		System.out.println(data.get(2).getAsString());
	        		System.out.println(data.get(3).getAsString());
	        		
	        		CourseAllocObject cao = new CourseAllocObject(data.get(1).getAsString(), data.get(2).getAsString(), data.get(3).getAsString());
	        		course_allocs.add(cao);
	        	}
	        	catch(UnsupportedOperationException e)
	        	{
	        		System.out.println(e);
	        	}
			}
    	}
    	catch (Exception e) {
    		System.out.println(e);
		}
		return course_allocs;
	}
	

}
