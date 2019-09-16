package com.alphanomad.AN_Webapp;

import com.google.gson.JsonObject;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class AdminMainView extends VerticalLayout implements View{
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
		
		DBHelper dbh = new DBHelper();
		String[] parameters= { "table", "target", "confirmed" };
		String[] values = { "TUTOR_ALLOC", "*", "0" };
		
		String result = dbh.php_request("select_booking", parameters, values);
		System.out.println("result is :");
		System.out.println(result);
		JsonObject tutor_alloc_obj = dbh.parse_json_string(result);
		
		try
    	{
    		JsonObject data = tutor_alloc_obj.getAsJsonArray("result").get(0).getAsJsonObject();
        	
        	
        	
        	for(String j : data.keySet())
        	{
        		try
        		{
        			System.out.println(data.get(j).getAsString()+"\n");
        		}
        		catch(UnsupportedOperationException e)
        		{
        			
        		}
        		
        	}
    	}
    	catch (Exception e) {
			// TODO: handle exception
		}
    	
		
		
		Grid g = new Grid<String>();
		//g.setItems(items);
		
		
	}

}
