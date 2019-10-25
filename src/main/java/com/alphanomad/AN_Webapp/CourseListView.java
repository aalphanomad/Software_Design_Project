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

public class CourseListView extends VerticalLayout implements View { // Global Variables
	ArrayList<TutorItem> Tutor = new ArrayList<TutorItem>();
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
	public void enter(ViewChangeEvent event) {// Added ensure that additional textfoelds and buttons are not added if
												// the user continuously clickes the "delete a course" button
		Added = false;
		view_courses();
	}

	public void AddNewCourse() {
		// Gets the detials of the currently logged in user
		UserInfo me = ((MyUI) getUI()).get_user_info();
		String mySN = me.get_student_num();

		// Creates the buttons and Textfields to allow the admin to add a new course
		CourseName = new TextField();
		CourseCode = new TextField();
		Password = new PasswordField();
		CourseName.setCaption("Name of Course to Add");
		CourseCode.setCaption("Course Code of Course to Add");
		Password.setCaption("Enter Your Password to Confirm");
		CourseName.setWidth("40%");
		CourseCode.setWidth("40%");
		Password.setWidth("40%");

		// The code below specifies what happens when we attemp to add a new course
		Button Confirm = new Button("Confirm", event -> { // Ensures that Errors are refreshed everytime the "Confirm"
															// button is selcetd
			CourseName.setComponentError(null);
			CourseCode.setComponentError(null);
			Password.setComponentError(null);
			// Below just checks if there are errors presents such as absence of values or
			// entering details of a currently existing course
			Boolean valid = true;
			if (CourseName.isEmpty()) {
				CourseName.setComponentError(new UserError("Please Enter a Course Name"));
				valid = false;
			}
			if (CourseCode.isEmpty()) {
				CourseCode.setComponentError(new UserError("Please Enter a Course Code"));

				valid = false;
			}
			if (Password.isEmpty()) {
				Password.setComponentError(new UserError("Please Enter Your Correct Password"));
				valid = false;
			}
			if (valid) {

				String[] params = { "adminUsername", "adminPassword", "course_name", "course_code", "event" };
				String[] values = { mySN, Password.getValue(),
						WordUtils.capitalize(CourseName.getValue().toLowerCase()), CourseCode.getValue().toUpperCase(),
						"1" };
				DBHelper dbh = new DBHelper();
				String ans = dbh.php_request("ManageCourses", params, values);
				// The code above send the data to the database to add the new course
				result = dbh.parse_json_string(ans);

				// Checks if there were any issues when adding the new course
				// Sample Output:{"user":0,"course":0,"delete":-1,"edit":0}
				if (result.get("user").toString().equals("0") && result.get("course").toString().equals("0")) {
					Notification.show("Course Added Successfully");
					enter(null);

				}
				// If the password entered is incorrect, rise an error
				if (!result.get("user").toString().equals("0")) {
					Password.setComponentError(new UserError("Please Enter Your Correct Password"));
				} else {
					CourseName.setComponentError(
							new UserError("A Course With This Course Name Or Course Code Already Exists"));
					CourseCode.setComponentError(
							new UserError("A Course With This Course Name Or Course Code Already Exists"));
				}

			}

		});
		addComponent(CourseName);
		addComponent(CourseCode);
		addComponent(Password);
		addComponent(Confirm);
	}

	public void DeleteCourses() {
		PasswordField Password = new PasswordField();
		Password.setComponentError(null);

		// switch to multiselect mode

		g.setSelectionMode(SelectionMode.MULTI);
		Notification.show("Please Select the Courses You Would Like to Delete Then Confirm Below.");
		// Gets all the courses that we have selected to delete
		g.addSelectionListener(event -> {
			DeleteCourses = event.getAllSelectedItems();
		});
		// adds a field to the admin to enter their password so that only admins can
		// manage courses
		Password.setCaption("Please Enter Your Password to Confirm.");
		Button Confirm = new Button("Confirm", event -> {// Checks if tleast one course is selected
			if (DeleteCourses.isEmpty()) {
				g.setComponentError(new UserError("Please Select Atleast one Course You Would Like To Delete."));
			}
			if (Password.isEmpty()) {// Checks if the password entered is correct
				Password.setComponentError(new UserError("Please Enter The Admin's Password!"));
			} else {
				DBHelper dbh = new DBHelper();
				UserInfo me = ((MyUI) getUI()).get_user_info();

				String[] params = { "adminUsername", "adminPassword", "course_code", "course_name", "event" };
				// Delete every selected course
				for (CourseItem i : DeleteCourses) {
					String[] values = { me.student_num, Password.getValue(), i.course_code, i.course_name, "2" };
					String ans = dbh.php_request("ManageCourses", params, values);
					// Sample Output:{"user":0,"course":0,"delete":0,"edit":0}
					result = dbh.parse_json_string(ans);
					if (!result.get("user").toString().equals("0"))
						Password.setComponentError(new UserError("Please Enter Your Admin Password"));
					else {
						Notification.show("Courses Have Been Deleted Successfully");
						enter(null);
					}

				}
			}
		});
		// checks that we have already added the textfield for the admin to enter their
		// password.
		if (Added == false) {
			addComponent(Password);
			addComponent(Confirm);
			Added = true;
		}

	}

	@SuppressWarnings("unchecked")
	public void EditCourse() {

		// switch to multiselect mode

		g.setSelectionMode(SelectionMode.SINGLE);

		// Checks that we do not add the "edit" column if it has already been added
		if (g.getColumn("edit") == null) {
			g.addColumn(unused -> "Edit",

					new ButtonRenderer(e -> {
						removeAllComponents();
						// Get the users details
						UserInfo me = ((MyUI) getUI()).get_user_info();
						String mySN = me.get_student_num();
						theCourse = (((CourseItem) e.getItem()).getCourse_name());
						theCode = (((CourseItem) e.getItem()).getCourse_code());

						Label test2 = new Label(
								"<p style = \"font-family:georgia,garamond,serif;font-size:30px;\">\r\n"
										+ "       <b><u>Editing Course: " + "<u>" + theCourse + "</u></b>" + "    </p>",
								ContentMode.HTML);
						addComponent(test2);
						// Sets up the textfields to the user to enter the edited details of the course
						TextField name = new TextField();
						name.setCaption("Please Enter The Course Name");
						name.setWidth("40%");
						name.setValue(theCourse);
						addComponent(name);

						TextField code = new TextField();
						code.setCaption("Please Enter the Course Code");
						code.setWidth("40%");
						code.setValue(theCode);
						addComponent(code);

						PasswordField admin = new PasswordField();
						admin.setCaption("Please Enter Your Admin Password");
						admin.setWidth("40%");
						addComponent(admin);

						Button Confirm = new Button("Confirm", event -> {// Below performs validation to ensure that all
																			// the necessary fields have been entered
																			// and that duplicated data is not entered
							name.setComponentError(null);
							code.setComponentError(null);
							admin.setComponentError(null);
							Boolean valid = true;
							if (name.isEmpty()) {
								name.setComponentError(new UserError("Please Enter a Course Name"));
								valid = false;
							}
							if (code.isEmpty()) {
								code.setComponentError(new UserError("Please Enter a Course Code"));

								valid = false;
							}
							if (admin.isEmpty()) {
								admin.setComponentError(new UserError("Please Enter Your Correct Password"));
								valid = false;
							}
							// Only is there are no errors, we proceed below to enter the new details in the
							// database
							if (valid) {
								DBHelper dbh = new DBHelper();

								String[] params = { "adminUsername", "adminPassword", "course_name", "course_code",
										"new_course_name", "new_course_code", "event" };
								String[] values = { mySN, admin.getValue().toString(),
										WordUtils.capitalize(theCourse.toLowerCase()), theCode.toUpperCase(),
										name.getValue().toString(), code.getValue().toString(), "3" };
								String ans = dbh.php_request("ManageCourses", params, values);
								result = dbh.parse_json_string(ans);
								// Below if for the case is no errors have occurred
								if (result.get("user").toString().equals("0")) {
									Notification.show("Course Editted Successfully");
									getUI().getNavigator().navigateTo("adminmain");
								} else {
									admin.setComponentError(new UserError("Please Enter Your Correct Password"));
								}

							}

						});
						addComponent(Confirm);

					})).setId("edit");
		}
	}

	@SuppressWarnings("unchecked")
	private void view_courses() {
		components.clear();

		// grid is just a table to store/display our data
		g = new Grid<CourseItem>(CourseItem.class);
		ArrayList<CourseItem> course_list = get_all_courses();
		// without these lines the grid only takes up a small section of the screen
		g.setSizeFull();
		g.setHeightByRows(course_list.size());

		// this is where we set the data for the grid
		g.setItems(course_list);
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

					if (!Arrays.toString(SN).equals("[]")) {
						Label test = new Label(
								"<p style = \"font-family:georgia,garamond,serif;font-size:30px;\">\r\n"
										+ "       <b><u>Lecturers of: </u></b> " + "<u>"
										+ (((CourseItem) e.getItem()).course_name).toString() + "</u>" + "      </p>",
								ContentMode.HTML);
						addComponent(test);
						
						 						
						  					for (int i = 0; i < names.length; i++)
					  					{
						  						Tutor.add(new TutorItem(names[i].substring(1, names[i].length() - 1),
						  								SN[i].substring(1, SN[i].length() - 1)));
										}
										
										for (int i = 0; i < names_Lecturer.length; i++)
					 					{
											Lecturer.add(new TutorItem(names_Lecturer[i].substring(1, names_Lecturer[i].length() - 1),
													SN2[i].substring(1, SN2[i].length() - 1)));
						  					}
										
					 					
					 					Grid<TutorItem> grid_Lecturer = new Grid<>(TutorItem.class);
					 					grid_Lecturer.getColumn("image").setRenderer(new ImageRenderer());
					 					grid_Lecturer.setColumnOrder("image", "name", "student_num");
					 					grid_Lecturer.setWidth("100%");
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
