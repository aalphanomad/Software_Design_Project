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
		ArrayList<CourseItem>me=new ArrayList<>();
		return me;
	}
}
