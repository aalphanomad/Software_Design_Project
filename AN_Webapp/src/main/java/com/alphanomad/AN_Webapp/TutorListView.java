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

		Button make_admin_btn = new Button("Make Selected users admin", event ->
		{
			if (selected_users != null)
			{
				DBHelper dbh = new DBHelper();
				String[] params = { "student_num" };

				for (UserItem user : selected_users)
				{
					// we can't make a tutor admin
					// nor can we make an admin or lectureradmin more of an admin
					if (user.getRole().equals("1"))
					{
						String[] vals = { user.getStudent_num() };
						// this sets the lecturer to a lectureradmin
						dbh.php_request("set_admin", params, vals);
						g.setItems(get_all_users());
					} else
					{
						Notification.show("Only Lecturers can be made admin");
						g.setItems(get_all_users());
					}

				}
			} else
			{
				Notification.show("No Items Selected");
			}

		});
		
		g.addColumn(unused -> "More Info", new ButtonRenderer<Object>(event ->
		{
			// UserInfo test = new UserInfo("1");
			// System.out.println("LECTVIEW: " + test.student_num);

			new ProfileView((MyUI) getUI(), (((UserItem) event.getItem()).getStudent_num()));
			getUI().getNavigator().navigateTo("profile");
		}));


		addComponent(make_admin_btn);

		// We'll probably need this in the future
		// TODO make the set_lecturer endpoint
		// and addthis to the component list
		Button make_lecturer_btn = new Button("Make Selected users lecturers", event ->
		{
			if (selected_users != null)
			{
				DBHelper dbh = new DBHelper();
				String[] params = { "student_num" };

				for (UserItem user : selected_users)
				{
					if (user.getRole().equals("3") || user.getRole().equals("1"))
					{
						String[] vals = { user.getStudent_num() };
						dbh.php_request("set_lecturer", params, vals);
						g.setItems(get_all_users());
					} else
					{
						Notification.show("Only Lecturers can be made admin");
						g.setItems(get_all_users());
					}

				}
			} else
			{
				Notification.show("No Items Selected");
			}
		});
		
		Button filter_tutor_button = new Button("view only tutors", event ->
		{
			g.setItems(get_selected_users("0"));

		});
		
		Button filter_lecturer_button = new Button("view only lecturers", event ->
		{
			g.setItems(get_selected_users("1"));

		});
		
		Button filter_admin_button = new Button("view only admins", event ->
		{
			ArrayList<UserItem> items = get_selected_users("2");
			items.addAll(get_selected_users("3"));
			g.setItems(items);

		});
		
		Button no_filter_button = new Button("view ALL user", event ->
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
		addComponent(go_back_to_main_view);
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
					else if(data.get(2).getAsString().equals("4")) {
						 user = new UserItem(data.get(0).getAsString(), data.get(1).getAsString(),"Super Admin");
						}
					
				
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

	}*/
}
