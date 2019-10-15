package com.alphanomad.AN_Webapp;

import java.util.ArrayList;
import java.util.Set;

import com.google.gson.JsonArray;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.renderers.ButtonRenderer;

public class TutorListView extends VerticalLayout implements View
{
	Set<UserItem> selected_users;
	
	public TutorListView()
	{
	}
	
	/*@Override
	public void enter(ViewChangeEvent event)
	{
		view_users();
	}*/
	
	
	/*private void view_users()
	{
		components.clear();
		Grid<UserItem> g = new Grid(UserItem.class);
		ArrayList<UserItem> course_list = get_all_users();
		g.setSizeFull();
		g.setColumnOrder("name","student_num","role");
		//g.setHeightByRows(course_list.size());
		g.setItems(course_list);


		// switch to multiselect mode
		g.setSelectionMode(SelectionMode.MULTI);
		g.addSelectionListener(event ->
		{
			selected_users = event.getAllSelectedItems();
			// Notification.show(selected_course_allocs.size() + " items selected");
		});
		
		addComponent(g);	

	}*/
	
	
	/*ArrayList<UserItem> get_all_users()
	{
		DBHelper dbh = new DBHelper();
		String[] parameters = { "table", "target", "filter", "value" };
		// the to 1's add a WHERE 1=1 clause to the query
		// it's basically a hacky way of excluding the mandatory WHERE clause in the php
		String[] values = { "USER_INFORMATION", "NAME,STUDENT_NUM,ROLE", "1", "1" };

		String result = dbh.php_request("generic_select", parameters, values);

		JsonArray user_arr;

	}*/
	
	/*ArrayList<UserItem> get_selected_users(String role)
	{
		DBHelper dbh = new DBHelper();
		String[] parameters = { "table", "target", "filter", "value" };
		// the to 1's add a WHERE 1=1 clause to the query
		// it's basically a hacky way of excluding the mandatory WHERE clause in the php
		String[] values = { "USER_INFORMATION", "NAME,STUDENT_NUM,ROLE", "ROLE", role };

		String result = dbh.php_request("generic_select", parameters, values);

		JsonArray user_arr;


	}*/
}
