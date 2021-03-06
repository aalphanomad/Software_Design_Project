package com.alphanomad.AN_Webapp;

import java.util.ArrayList;
import java.util.Set;

import com.google.gson.JsonArray;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.data.sort.SortDirection;
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
	
	
	ArrayList<CourseAllocObject> get_declined_course_allocs(){

		DBHelper dbh = new DBHelper();
		String[] parameters = { "table", "target", "filter", "value" };
		String[] values = { "USER_COURSE_ALLOC", "*", "CONFIRMED", "-1" };

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
							data.get(2).getAsString(), data.get(3).getAsString(),data.get(4).getAsString(),data.get(5).getAsString());
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
							data.get(2).getAsString(), data.get(3).getAsString(),data.get(4).getAsString(),data.get(5).getAsString());
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
							data.get(2).getAsString(), data.get(3).getAsString(),data.get(4).getAsString(),data.get(5).getAsString());
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
							data.get(2).getAsString(), data.get(3).getAsString(),data.get(4).getAsString(),data.get(5).getAsString());
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
