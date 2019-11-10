package com.alphanomad.AN_Webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import org.apache.commons.lang.WordUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.UserError;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.ImageRenderer;

public class CourseListView extends VerticalLayout implements View {
	//Initialize the list to display the lecturers for a specified course
	ArrayList<TutorItem> Tutor = new ArrayList<TutorItem>();
	//Initialize the list to display the tutors for a specified course
	
	ArrayList<TutorItem> Lecturer = new ArrayList<TutorItem>();
	JsonObject result_Lecturer;
	Set<UserItem> selected_users;
	JsonObject result;
	Grid<CourseItem> g;
	Set<CourseItem> DeleteCourses;
	String theCourse = "";
	String theCode = "";
	TextField CourseName, CourseCode;
	PasswordField Password;
	public static Boolean Added = false;

	public CourseListView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// Added ensures that additional textfields and buttons are not added if
	// the user continuously clicks the "delete a course" button
		Added = false;
		view_courses();
	}

	public void AddNewCourse() {
		
	}

	public void DeleteCourses() {

	}

	@SuppressWarnings("unchecked")
	public void EditCourse() {

	}

	@SuppressWarnings("unchecked")
	public void view_courses() {

	}

	// I (mayur) have not idea what is going on below, this is Elgoni's code, please
	// ask him to comment it if you need it to be.
	ArrayList<CourseItem> get_all_courses() {
		DBHelper dbh = new DBHelper();
		String[] parameters = { "table", "target", "filter", "value" };
		// the to 1's add a WHERE 1=1 clause to the query
		// it's basically a hacky way of excluding the mandatory WHERE clause in the php
		String[] values = { "COURSES", "COURSE_CODE,COURSE_NAME", "1", "1" };

		String result = dbh.php_request("generic_select", parameters, values);

		JsonArray course_arr;
		try {
			course_arr = dbh.parse_json_string_arr(result);
		} catch (Exception e) {
			return new ArrayList<CourseItem>();
		}
		ArrayList<CourseItem> course_items = new ArrayList<CourseItem>();

		try {
			for (int x = 0; x < course_arr.size(); x++) {
				JsonArray data = (JsonArray) course_arr.getAsJsonArray().get(x);
				try {
					System.out.println(data.get(0).getAsString());
					System.out.println(data.get(1).getAsString());

					CourseItem course = new CourseItem(data.get(0).getAsString(), data.get(1).getAsString());
					course_items.add(course);
				} catch (UnsupportedOperationException e) {
					System.out.println(e);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return course_items;
	}
}
