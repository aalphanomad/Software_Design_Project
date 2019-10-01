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

public class CourseAllocationView extends VerticalLayout implements View
{
	Set<CourseAllocObject> selected_course_allocs;

	public CourseAllocationView()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public void enter(ViewChangeEvent vcevent)
	{
		// clear the screen
		components.clear();

		Grid<CourseAllocObject> g = new Grid<CourseAllocObject>(CourseAllocObject.class);
		g.setSizeFull();

		g.setItems(get_unconfirmed_course_allocs());
		/*
		 * g.addColumn(CourseAllocObject::getId).setCaption("Application ID");
		 * g.addColumn(CourseAllocObject::getStud_num).setCaption("Student Number");
		 * g.addColumn(CourseAllocObject::getCourse).setCaption("Course");
		 * g.addColumn(CourseAllocObject::getConfirmed).setCaption("Status");
		 */
		g.getColumn("id").setWidthUndefined();
		// switch to multiselect mode
		g.setSelectionMode(SelectionMode.MULTI);

		g.addSelectionListener(event ->
		{
			selected_course_allocs = event.getAllSelectedItems();
			Notification.show(selected_course_allocs.size() + " items selected");
		});
		g.setColumnOrder("id", "stud_num", "course", "confirmed");
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
			getUI().getNavigator().navigateTo("adminmain");

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

					CourseAllocObject cao = new CourseAllocObject(data.get(0).getAsString(), data.get(1).getAsString(),
							data.get(2).getAsString(), data.get(3).getAsString());
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

					CourseAllocObject cao = new CourseAllocObject(data.get(0).getAsString(), data.get(1).getAsString(),
							data.get(2).getAsString(), data.get(3).getAsString());
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
					CourseAllocObject cao = new CourseAllocObject(data.get(0).getAsString(), data.get(1).getAsString(),
							data.get(2).getAsString(), data.get(3).getAsString());
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

}
