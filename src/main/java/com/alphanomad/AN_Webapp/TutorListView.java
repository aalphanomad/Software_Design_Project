package com.alphanomad.AN_Webapp;

import java.util.ArrayList;
import java.util.Set;

import com.google.gson.JsonArray;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.renderers.ButtonRenderer;

public class TutorListView extends VerticalLayout implements View
{
	Set<UserItem> selected_users;
	public ComboBox<String> cb = new ComboBox<>();

	
	
	public TutorListView()
	{
	}
	
	@Override
	public void enter(ViewChangeEvent event)
	{
		view_users();
	}
	
	
	public void view_users()
	{
		cb.setComponentError(null);
		components.clear();
		Grid<UserItem> g = new Grid(UserItem.class);
		ArrayList<UserItem> course_list = get_all_users();
		g.setSizeFull();
		g.setColumnOrder("name","student_num","role");
		//g.setHeightByRows(course_list.size());
		g.setItems(course_list);


		// switch to multiselect mode
		g.setSelectionMode(SelectionMode.MULTI);
		
		
		addComponent(g);
		
		/*HorizontalLayout horiz = new HorizontalLayout();
		
		Button no_filter_button = new Button("view ALL users", event ->
		{
			g.setItems(get_all_users());

		});
		
		Button go_back_to_main_view = new Button("Return to menu", event ->
		{
			getUI().getNavigator().navigateTo("adminmain");

		});
		
		HorizontalLayout button_row = new HorizontalLayout();
		button_row.addComponent(filter_tutor_button);
		button_row.addComponent(filter_lecturer_button);
		button_row.addComponent(filter_admin_button);
		button_row.addComponent(no_filter_button);
		addComponent(button_row);
		addComponent(go_back_to_main_view);*/
	}
	
	
	ArrayList<UserItem> get_all_users()
	{
		DBHelper dbh = new DBHelper();
		String[] parameters = { "table", "target", "filter", "value" };
		// the to 1's add a WHERE 1=1 clause to the query
		// it's basically a hacky way of excluding the mandatory WHERE clause in the php
		String[] values = { "USER_INFORMATION", "NAME,STUDENT_NUM,ROLE", "1", "1" };

		String result = dbh.php_request("generic_select", parameters, values);

		JsonArray user_arr;
		try
		{
			user_arr = dbh.parse_json_string_arr(result);
		} catch (Exception e)
		{
			return new ArrayList<UserItem>();
		}
		ArrayList<UserItem> user_items = new ArrayList<UserItem>();
		UserItem user = null;
		try
		{
			for (int x = 0; x < user_arr.size(); x++)
			{
				JsonArray data = (JsonArray) user_arr.getAsJsonArray().get(x);
				try
				{
					
					if(data.get(2).getAsString().equals("0")) {
					 user = new UserItem(data.get(0).getAsString(), data.get(1).getAsString(),"Tutor");
					}
					else if(data.get(2).getAsString().equals("1")) {
						 user = new UserItem(data.get(0).getAsString(), data.get(1).getAsString(),"Lecturer");
						}
					else if(data.get(2).getAsString().equals("2")) {
						 user = new UserItem(data.get(0).getAsString(), data.get(1).getAsString(),"Admin");
						}
					else if(data.get(2).getAsString().equals("3")) {
						 user = new UserItem(data.get(0).getAsString(), data.get(1).getAsString(),"Lecturer/Admin");
						}
					/*else if(data.get(2).getAsString().equals("4")) {
						 user = new UserItem(data.get(0).getAsString(), data.get(1).getAsString(),"Super Admin");
						}*/
					
				
					user_items.add(user);
				} catch (UnsupportedOperationException e)
				{
					System.out.println(e);
				}
			}
		} catch (Exception e)
		{
			System.out.println(e);
		}
		return user_items;

	}
	
	ArrayList<UserItem> get_selected_users(String role)
	{
		DBHelper dbh = new DBHelper();
		String[] parameters = { "table", "target", "filter", "value" };
		// the to 1's add a WHERE 1=1 clause to the query
		// it's basically a hacky way of excluding the mandatory WHERE clause in the php
		String[] values = { "USER_INFORMATION", "NAME,STUDENT_NUM,ROLE", "ROLE", role };

		String result = dbh.php_request("generic_select", parameters, values);

		JsonArray user_arr;
		try
		{
			user_arr = dbh.parse_json_string_arr(result);
		} catch (Exception e)
		{
			return new ArrayList<UserItem>();
		}
		ArrayList<UserItem> user_items = new ArrayList<UserItem>();

		try
		{
			for (int x = 0; x < user_arr.size(); x++)
			{
				JsonArray data = (JsonArray) user_arr.getAsJsonArray().get(x);
				try
				{
					UserItem user = new UserItem(data.get(0).getAsString(), data.get(1).getAsString(),
							data.get(2).getAsString());
					user_items.add(user);
				} catch (UnsupportedOperationException e)
				{
					System.out.println(e);
				}
			}
		} catch (Exception e)
		{
			System.out.println(e);
		}
		return user_items;

	}
}
