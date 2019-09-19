package com.alphanomad.AN_Webapp;

import java.util.ArrayList;
import java.util.Set;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class AdminMainView extends VerticalLayout implements View
{
	// for some reason I decided to do all this stuff in one view
	// When I made this decision only myself and god knew the reason
	// now only god knows.

	// what is this for?
	// https://tinyurl.com/yay5jktn
	Set<CourseAllocObject> selected_course_allocs;
	Set<UserItem> selected_users;

	public AdminMainView()
	{

	}

	@Override
	public void enter(ViewChangeEvent vc_event)
	{
		// empty the screen just in case
		components.clear();
		Button view_application_btn = new Button("view pending applications", btn_event -> view_applications());
		Button view_users_btn = new Button("view all users", btn_event -> view_users());
		Button view_courses_btn = new Button("view all courses", btn_event -> view_courses());
		Button login = new Button("Logout", event -> getUI().getNavigator().navigateTo("login"));
		addComponent(view_application_btn);
		addComponent(view_users_btn);
		addComponent(view_courses_btn);
		addComponent(login);
		
		
		// This is the code used to migrate the old courses data to the new table
		// Don't uncomment this unless you know what you are doing
		/*
		Button magic_btn = new Button("Magic Button", event -> {
			ArrayList<UserItem> users = get_all_users();
			DBHelper dbh = new DBHelper();
			for(UserItem user : users)
			{
				String[] params = { "name", "student_num"};
				String[] values = { user.getName(), user.getStudent_num() };
				String courses = dbh.php_request("get_courses", params, values);
				JsonObject courses_obj = new JsonObject();
				
				try
				{
					courses_obj = dbh.parse_json_string(courses);
				} catch (Exception e)
				{
					System.out.println(e);
				}
				
				try
				{
					JsonObject data = courses_obj.getAsJsonArray("result").get(0).getAsJsonObject();

					for (String j : data.keySet())
					{
						try
						{
							System.out.println(data.get(j).getAsString() + "\n");
							String[] params2 = { "student_num", "course", "confirmed"};
							String[] values2 = { user.getStudent_num(), data.get(j).getAsString(), "0"};
							
							dbh.php_request("update_courses", params2, values2);
						} catch (UnsupportedOperationException e)
						{
							//System.out.println(e);
						}

					}
				} catch (Exception e)
				{
					System.out.println(e);
				}
			}
			
		});
		
		addComponent(magic_btn);
		*/
	}

	private void view_courses()
	{
		components.clear();

		// grid is just a table to store/display our data
		Grid<CourseItem> g = new Grid<CourseItem>();
		ArrayList<CourseItem> course_list = get_all_courses();
		// without these lines the grid only takes up a small section of the screen
		g.setSizeFull();
		g.setHeightByRows(course_list.size());

		// this is where we set the data for the grid
		g.setItems(course_list);
		g.addColumn(CourseItem::getCourse_code).setCaption("Course Code");
		g.addColumn(CourseItem::getCourse_name).setCaption("Course Name");
		addComponent(g);

		// this just goes back to the menu that is first shown
		Button go_back_to_main_view = new Button("Return to menu", event ->
		{
			enter(null);

		});
		addComponent(go_back_to_main_view);

	}

	private void view_users()
	{
		components.clear();
		Grid<UserItem> g = new Grid<UserItem>();
		ArrayList<UserItem> course_list = get_all_users();
		g.setSizeFull();
		//g.setHeightByRows(course_list.size());
		g.setItems(course_list);
		g.addColumn(UserItem::getStudent_num).setCaption("Student/Staff Number");
		g.addColumn(UserItem::getName).setCaption("Name");
		g.addColumn(UserItem::getRole).setCaption("Role");

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
			if (selected_course_allocs != null)
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
			enter(null);

		});
		
		HorizontalLayout button_row = new HorizontalLayout();
		button_row.addComponent(filter_tutor_button);
		button_row.addComponent(filter_lecturer_button);
		button_row.addComponent(filter_admin_button);
		button_row.addComponent(no_filter_button);
		addComponent(button_row);
		addComponent(go_back_to_main_view);
	}

	private void view_applications()
	{
		// clear the screen
		components.clear();

		Grid<CourseAllocObject> g = new Grid<CourseAllocObject>();
		g.setSizeFull();
		g.setItems(get_unconfirmed_course_allocs());
		g.addColumn(CourseAllocObject::getStud_num).setCaption("Student Number");
		g.addColumn(CourseAllocObject::getCourse).setCaption("Course");
		g.addColumn(CourseAllocObject::getConfirmed).setCaption("Status");

		// switch to multiselect mode
		g.setSelectionMode(SelectionMode.MULTI);

		g.addSelectionListener(event ->
		{
			selected_course_allocs = event.getAllSelectedItems();
			Notification.show(selected_course_allocs.size() + " items selected");
		});
		addComponent(g);

		Button confirm_btn = new Button("Confirm selected applications", event ->
		{
			if (selected_course_allocs != null)
			{
				DBHelper dbh = new DBHelper();
				String[] params = { "student_num", "course", "confirmed" };

				for (CourseAllocObject cao : selected_course_allocs)
				{
					if (cao.confirmed.equals("0") || cao.confirmed.equals("3"))
					{
						String[] vals = { cao.getStud_num(), cao.getCourse(), "1" };
						dbh.php_request("update_courses", params, vals);
						g.setItems(get_unconfirmed_course_allocs());
					}

				}
			} else
			{
				Notification.show("No Items Selected");
			}

		});
		
		Button deny_btn = new Button("Deny selected applications", event ->
		{
			if (selected_course_allocs != null)
			{
				DBHelper dbh = new DBHelper();
				String[] params = { "student_num", "course", "confirmed" };

				for (CourseAllocObject cao : selected_course_allocs)
				{
					if (cao.confirmed.equals("1"))
					{
						String[] vals = { cao.getStud_num(), cao.getCourse(), "0" };
						dbh.php_request("update_courses", params, vals);
						g.setItems(get_confirmed_course_allocs());
					}

				}
			} else
			{
				Notification.show("No Items Selected");
			}

		});

		Button view_confirmed_btn = new Button("View Confirmed Applications", event ->
		{
			g.setItems(get_confirmed_course_allocs());

		});

		Button view_unconfirmed_btn = new Button("View Unconfirmed Applications", event ->
		{
			g.setItems(get_unconfirmed_course_allocs());

		});

		Button view_all_btn = new Button("View ALL Applications", event ->
		{
			g.setItems(get_all_course_allocs());

		});

		HorizontalLayout conf_deny_row = new HorizontalLayout();
		HorizontalLayout filter_row = new HorizontalLayout();
		conf_deny_row.addComponent(confirm_btn);
		conf_deny_row.addComponent(deny_btn);
		filter_row.addComponent(view_confirmed_btn);
		filter_row.addComponent(view_unconfirmed_btn);
		filter_row.addComponent(view_all_btn);
		addComponent(conf_deny_row);
		addComponent(filter_row);
		

		Button go_back_to_main_view = new Button("Return to menu", event ->
		{
			enter(null);

		});
		addComponent(go_back_to_main_view);

	}

	ArrayList<CourseAllocObject> get_unconfirmed_course_allocs()
	{
		DBHelper dbh = new DBHelper();
		String[] parameters = { "table", "target", "filter", "value" };
		String[] values = { "USER_COURSE_ALLOC", "*", "CONFIRMED", "0" };

		String result = dbh.php_request("generic_select", parameters, values);
		JsonArray tutor_alloc_obj;
		try
		{
			// I think that this is an json_array of json_arrays
			tutor_alloc_obj = dbh.parse_json_string_arr(result);
		} catch (Exception e)
		{
			// if the query fails that means that the query returned nothing
			return new ArrayList<CourseAllocObject>();
		}

		ArrayList<CourseAllocObject> course_allocs = new ArrayList<CourseAllocObject>();

		try
		{
			// we loop through all of the results from our query
			for (int x = 0; x < tutor_alloc_obj.size(); x++)
			{
				JsonArray data = (JsonArray) tutor_alloc_obj.getAsJsonArray().get(x);
				try
				{

					CourseAllocObject cao = new CourseAllocObject(data.get(1).getAsString(), data.get(2).getAsString(),
							data.get(3).getAsString());
					course_allocs.add(cao);
				} catch (UnsupportedOperationException e)
				{
					System.out.println(e);
				}
			}
		} catch (Exception e)
		{
			System.out.println(e);
		}
		return course_allocs;
	}

	ArrayList<CourseAllocObject> get_confirmed_course_allocs()
	{
		DBHelper dbh = new DBHelper();
		String[] parameters = { "table", "target", "filter", "value" };
		String[] values = { "USER_COURSE_ALLOC", "*", "CONFIRMED", "1" };

		String result = dbh.php_request("generic_select", parameters, values);
		JsonArray tutor_alloc_obj;
		try
		{
			tutor_alloc_obj = dbh.parse_json_string_arr(result);
		} catch (Exception e)
		{
			return new ArrayList<CourseAllocObject>();
		}

		ArrayList<CourseAllocObject> course_allocs = new ArrayList<CourseAllocObject>();

		try
		{
			for (int x = 0; x < tutor_alloc_obj.size(); x++)
			{
				JsonArray data = (JsonArray) tutor_alloc_obj.getAsJsonArray().get(x);
				try
				{
					System.out.println(data.get(1).getAsString());
					System.out.println(data.get(2).getAsString());
					System.out.println(data.get(3).getAsString());

					CourseAllocObject cao = new CourseAllocObject(data.get(1).getAsString(), data.get(2).getAsString(),
							data.get(3).getAsString());
					course_allocs.add(cao);
				} catch (UnsupportedOperationException e)
				{
					System.out.println(e);
				}
			}
		} catch (Exception e)
		{
			System.out.println(e);
		}
		return course_allocs;
	}

	ArrayList<CourseAllocObject> get_all_course_allocs()
	{
		DBHelper dbh = new DBHelper();
		String[] parameters = { "table", "target", "filter", "value" };
		String[] values = { "USER_COURSE_ALLOC", "*", "1", "1" };

		String result = dbh.php_request("generic_select", parameters, values);
		JsonArray tutor_alloc_obj;
		try
		{
			tutor_alloc_obj = dbh.parse_json_string_arr(result);
		} catch (Exception e)
		{
			return new ArrayList<CourseAllocObject>();
		}

		ArrayList<CourseAllocObject> course_allocs = new ArrayList<CourseAllocObject>();

		try
		{
			for (int x = 0; x < tutor_alloc_obj.size(); x++)
			{
				JsonArray data = (JsonArray) tutor_alloc_obj.getAsJsonArray().get(x);
				try
				{
					CourseAllocObject cao = new CourseAllocObject(data.get(1).getAsString(), data.get(2).getAsString(),
							data.get(3).getAsString());
					course_allocs.add(cao);
				} catch (UnsupportedOperationException e)
				{
					System.out.println(e);
				}
			}
		} catch (Exception e)
		{
			System.out.println(e);
		}
		return course_allocs;
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

	ArrayList<CourseItem> get_all_courses()
	{
		DBHelper dbh = new DBHelper();
		String[] parameters = { "table", "target", "filter", "value" };
		// the to 1's add a WHERE 1=1 clause to the query
		// it's basically a hacky way of excluding the mandatory WHERE clause in the php
		String[] values = { "COURSES", "COURSE_CODE,COURSE_NAME", "1", "1" };

		String result = dbh.php_request("generic_select", parameters, values);

		JsonArray course_arr;
		try
		{
			course_arr = dbh.parse_json_string_arr(result);
		} catch (Exception e)
		{
			return new ArrayList<CourseItem>();
		}
		ArrayList<CourseItem> course_items = new ArrayList<CourseItem>();

		try
		{
			for (int x = 0; x < course_arr.size(); x++)
			{
				JsonArray data = (JsonArray) course_arr.getAsJsonArray().get(x);
				try
				{
					System.out.println(data.get(0).getAsString());
					System.out.println(data.get(1).getAsString());

					CourseItem course = new CourseItem(data.get(0).getAsString(), data.get(1).getAsString());
					course_items.add(course);
				} catch (UnsupportedOperationException e)
				{
					System.out.println(e);
				}
			}
		} catch (Exception e)
		{
			System.out.println(e);
		}
		return course_items;
	}
}
