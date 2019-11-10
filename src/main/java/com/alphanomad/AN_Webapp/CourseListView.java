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
		components.clear();

		// grid is just a table to store/display our data
		g = new Grid<CourseItem>(CourseItem.class);
		ArrayList<CourseItem> course_list = get_all_courses();
		// without these lines the grid only takes up a small section of the screen
		g.setSizeFull();
		g.setHeightByRows(course_list.size());

		// this is where we set the data for the grid
		g.setItems(course_list);
		
		//when the admin clicks o view info, we provide a list of tutors and lecturers for the particular course
		g.addColumn(unused -> "View Info",
				// Allows the admin to view all the tutors and lecturers for a given course
				new ButtonRenderer(e -> {
					removeAllComponents();
					Tutor.clear();
					Lecturer.clear();
					DBHelper dbh = new DBHelper();
					DBHelper dbh2 = new DBHelper();

					String ans2 = dbh.php_request("AdminV_Tutors", new String[] { "course" },
							new String[] { (((CourseItem) e.getItem()).course_code) });
					result = dbh.parse_json_string(ans2);

					String ans_Lecturer = dbh2.php_request("AdminV_Lecturers", new String[] { "course" },
							new String[] { (((CourseItem) e.getItem()).course_code) });
					result_Lecturer = dbh2.parse_json_string(ans_Lecturer);
					String dirty_name = result.get("name").toString().substring(1,
							result.get("name").toString().length() - 1);

					String dirty_name_Lecturer = result_Lecturer.get("name").toString().substring(1,
							result_Lecturer.get("name").toString().length() - 1);
					// The unformatted names array which contains the names of the tutors/lecturers
					// for the selected course(Unformatted in the sense
					// this the string still contains inverted commas, curly brackets,etc

					// below is where I format the data for use
					String[] names = dirty_name.split(",");
					String[] names_Lecturer = dirty_name_Lecturer.split(",");

					String dirty_SN = result.get("student_num").toString().substring(1,
							result.get("student_num").toString().length() - 1);
					String[] SN = dirty_SN.split(",");

					String dirty_SN2 = result_Lecturer.get("student_num").toString().substring(1,
							result_Lecturer.get("student_num").toString().length() - 1);
					String[] SN2 = dirty_SN2.split(",");
					System.out.println("THE TUTOR ARRAY"+Arrays.toString(names));
					System.out.println("THE LECTURER ARRAY"+Arrays.toString(names_Lecturer));

					//if there exists lecturers for the course then update the grid with the arraylist of lecturers
					if (!Arrays.toString(names_Lecturer).equals("[]")) {
						Label test = new Label(
								"<p style = \"font-family:georgia,garamond,serif;font-size:30px;\">\r\n"
										+ "       <b><u>Lecturers of: </u></b> " + "<u>"
										+ (((CourseItem) e.getItem()).course_name).toString() + "</u>" + "      </p>",
								ContentMode.HTML);
						addComponent(test);
						
						 				
										for (int i = 0; i < names_Lecturer.length; i++)
					 					{
											Lecturer.add(new TutorItem(names_Lecturer[i].substring(1, names_Lecturer[i].length() - 1),
													SN2[i].substring(1, SN2[i].length() - 1)));
						  					}
										
					 					
					 					Grid<TutorItem> grid_Lecturer = new Grid<>(TutorItem.class);
					 					grid_Lecturer.getColumn("image").setRenderer(new ImageRenderer());
					 					grid_Lecturer.setColumnOrder("image", "name", "student_num");
					 					grid_Lecturer.setWidth("100%");
					 					grid_Lecturer.setHeight("190px");
											grid_Lecturer.setItems(Lecturer);
											
											grid_Lecturer.addColumn(unused -> "More Info", new ButtonRenderer(
						
													event ->
						 							{
														new ProfileView((MyUI) getUI(), (((TutorItem) event.getItem()).getStudent_num()));
						 								getUI().getNavigator().navigateTo("profile");
						 							}));
						 					
						 					
						 					grid_Lecturer.setRowHeight(100);
						 					grid_Lecturer.setHeaderRowHeight(30);
											
						 					addComponent(grid_Lecturer);
					//if no lecturers exist then display a message
					}else {
						Label test = new Label(
								"<p style = \"font-family:georgia,garamond,serif;font-size:30px;\">\r\n"
										+ "       <b><u>There are No Lecturers For : </u></b> " + "<u>"
										+ (((CourseItem) e.getItem()).course_name).toString() + "</u>" + "      </p>",
								ContentMode.HTML);
						addComponent(test);
					}
					
					//if there exists tutors for the course then update the grid with the arraylist of tutors
					if(!Arrays.toString(names).equals("[]")) {
						
	  					for (int i = 0; i < names.length; i++)
  					{
	  						Tutor.add(new TutorItem(names[i].substring(1, names[i].length() - 1),
	  								SN[i].substring(1, SN[i].length() - 1)));
					}
					
						
						 					
						 					Label test2 = new Label(
						 							"<p style = \"font-family:georgia,garamond,serif;font-size:30px;\">\r\n"
						 									+ "       <b><u>Tutors of: </u></b> " + "<u>"
						 									+ (((CourseItem) e.getItem()).course_name).toString() + "</u>" + "      </p>",
						 							ContentMode.HTML);
						 					addComponent(test2);
						//Sets up the grid to display the details of the tutors tutoring a given course
						Grid<TutorItem> grid = new Grid<>(TutorItem.class);
						grid.getColumn("image").setRenderer(new ImageRenderer());
						grid.setColumnOrder("image", "name", "student_num");
						grid.setWidth("100%");
						grid.setItems(Tutor);
						// We can select a user and view there profile
						grid.addColumn(unused -> "More Info", new ButtonRenderer(

								event -> {
									new ProfileView((MyUI) getUI(), (((TutorItem) event.getItem()).getStudent_num()));
									getUI().getNavigator().navigateTo("profile");
								}));
						grid.setRowHeight(100);
						grid.setHeaderRowHeight(30);

						addComponent(grid);

					//if no tutors exist then display a message
					} else {
						Label test = new Label(
								"<p style = \"font-family:georgia,garamond,serif;font-size:30px;\">\r\n"
										+ "       <b><u>There are No Tutors For : </u></b> " + "<u>"
										+ (((CourseItem) e.getItem()).course_name).toString() + "</u>" + "      </p>",
								ContentMode.HTML);
						addComponent(test);
					}
					Button home = new Button("Home", event -> {
						getUI().getNavigator().navigateTo("adminmain");
					});
					addComponent(home);
				}));

		// Ensures that the course courses are sorted in alphabetical order
		g.sort("course_code", SortDirection.ASCENDING);

		addComponent(g);
		Button AddCourses = new Button("Add New Course", event -> {
			removeAllComponents();
			AddNewCourse();

		});
		Button Delete = new Button("Delete A Course", event -> {

			DeleteCourses();

		});
		Button Edit = new Button("Edit A Course", event -> {

			EditCourse();
		});

		HorizontalLayout button_row = new HorizontalLayout();
		button_row.addComponent(AddCourses);
		button_row.addComponent(Delete);
		button_row.addComponent(Edit);
		addComponent(button_row);

		// this just goes back to the menu that is first shown
		Button go_back_to_main_view = new Button("Return to menu", event -> {
			getUI().getNavigator().navigateTo("adminmain");

		});
		addComponent(go_back_to_main_view);

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
