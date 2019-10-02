package com.alphanomad.AN_Webapp;

import java.util.ArrayList;
import java.util.Set;


import org.apache.commons.lang.WordUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vaadin.data.Binder;
import com.vaadin.data.Binder.Binding;
import com.vaadin.icons.VaadinIcons;
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
import com.vaadin.ui.components.grid.EditorImpl;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.ImageRenderer;

public class CourseListView extends VerticalLayout implements View
{
	ArrayList<TutorItem> Tutor = new ArrayList<TutorItem>();
	Set<UserItem> selected_users;
	JsonObject result;
	Grid<CourseItem> g;
	Set<CourseItem> DeleteCourses;

	public CourseListView()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public void enter(ViewChangeEvent event)
	{
		view_courses();
	}

	public void AddNewCourse()
	{
		TextField CourseName = new TextField();
		TextField CourseCode = new TextField();
		PasswordField Password = new PasswordField();
		CourseName.setCaption("Name of Course to Add");
		CourseCode.setCaption("Course Code of Course to Add");
		Password.setCaption("Enter password to Confirm");
		UserInfo me = ((MyUI) getUI()).get_user_info();
		String mySN = me.get_student_num();

		Button Confirm = new Button("Confirm", event ->
		{
			Boolean valid = true;
			if (CourseName.isEmpty())
			{
				CourseName.setComponentError(new UserError("Please Enter a Course Name"));
				valid = false;
			}
			if (CourseCode.isEmpty())
			{
				CourseCode.setComponentError(new UserError("Please Enter a Course Code"));

				valid = false;
			}
			if (Password.isEmpty())
			{
				Password.setComponentError(new UserError("Please Enter Your Correct Password"));
				valid = false;
			}
			if (valid)
			{
				String[] params = { "adminUsername", "adminPassword", "course_name", "course_code", "event" };
				String[] values = { mySN, Password.getValue(),
						WordUtils.capitalize(CourseName.getValue().toLowerCase()), CourseCode.getValue().toUpperCase(),
						"1" };
				DBHelper dbh = new DBHelper();
				String ans = dbh.php_request("ManageCourses", params, values);
				result = dbh.parse_json_string(ans);
				System.out.println(result.get("user"));
				System.out.println(result.get("course"));

				if (result.get("user").toString().equals("0") && result.get("course").toString().equals("0"))
				{
					Notification.show("Course Added Successfully");
					enter(null);

				} else
				{
					Notification.show("FAILED!");
				}

			}

		});
		addComponent(CourseName);
		addComponent(CourseCode);
		addComponent(Password);
		addComponent(Confirm);
	}

	public void DeleteCourses()
	{
		PasswordField Password = new PasswordField();
		Password.setComponentError(null);

		// switch to multiselect mode

		g.setSelectionMode(SelectionMode.MULTI);
		Notification.show("Please Select the Courses You Would Like to Delete Then Confirm Below.");
		g.addSelectionListener(event ->
		{
			DeleteCourses = event.getAllSelectedItems();
		});
		Password.setCaption("Please Enter You Password to Confirm.");
		Button Confirm = new Button("Confirm", event ->
		{
			if (DeleteCourses.isEmpty())
			{
				g.setComponentError(new UserError("Please Select Atleast one Course You Would Like To Delete."));
			}
			if (Password.isEmpty())
			{
				Password.setComponentError(new UserError("Please Enter The Admin's Password!"));
			} else
			{
				DBHelper dbh = new DBHelper();
				UserInfo me = ((MyUI) getUI()).get_user_info();

				String[] params = { "adminUsername", "adminPassword", "course_code", "course_name", "event" };

				for (CourseItem i : DeleteCourses)
				{
					String[] values = { me.student_num, Password.getValue(), i.course_code, i.course_name, "2" };
					String ans = dbh.php_request("ManageCourses", params, values);
					Notification.show(ans);

					result = dbh.parse_json_string(ans);
					if (!result.get("user").toString().equals("0"))
						Password.setComponentError(new UserError("The Password Does Not Correspond to The Admin's!"));
					else
					{
						Notification.show("Courses Have Been Deleted Successfully");
						enter(null);
					}

				}
			}
		});
		addComponent(Password);
		addComponent(Confirm);
	}

	public void EditCourse()
	{
		// switch to multiselect mode

		g.setSelectionMode(SelectionMode.SINGLE);
		g.addSelectionListener(event ->
		{
			Notification.show("HELLO");
			// DeleteCourses = event.
		});
	}

	@SuppressWarnings("unchecked")
	private void view_courses()
	{
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

				new ButtonRenderer(e ->
				{
					removeAllComponents();
					DBHelper dbh = new DBHelper();

					// new String[] { (((CourseItem) e.getItem()).course_code)});
					String ans2 = dbh.php_request("AdminV_Tutors", new String[] { "course" },
							new String[] { (((CourseItem) e.getItem()).course_code) });
					result = dbh.parse_json_string(ans2);
					String dirty_name = result.get("name").toString().substring(1,
							result.get("name").toString().length() - 1);
					String[] names = dirty_name.split(",");
					String dirty_SN = result.get("student_num").toString().substring(1,
							result.get("student_num").toString().length() - 1);
					String[] SN = dirty_SN.split(",");
					for (int i = 0; i < names.length; i++)
					{
						Tutor.add(new TutorItem(names[i].substring(1, names[i].length() - 1),
								SN[i].substring(1, SN[i].length() - 1)));
					}

					Label test = new Label(
							"<p style = \"font-family:georgia,garamond,serif;font-size:30px;\">\r\n"
									+ "       <b><u>Tutors of: </u></b> " + "<u>"
									+ (((CourseItem) e.getItem()).course_name).toString() + "</u>" + "      </p>",
							ContentMode.HTML);
					addComponent(test);
					Grid<TutorItem> grid = new Grid<>(TutorItem.class);
					grid.getColumn("image").setRenderer(new ImageRenderer());
					grid.setColumnOrder("image", "name", "student_num");
					grid.setWidth("100%");
					grid.setItems(Tutor);
					grid.addColumn(unused -> "More Info", new ButtonRenderer(

							event ->
							{
								new ProfileView((MyUI) getUI(), (((TutorItem) event.getItem()).getStudent_num()));
								getUI().getNavigator().navigateTo("profile");
							}));
					grid.setRowHeight(100);
					grid.setHeaderRowHeight(30);
					addComponent(grid);

				}));
		
	
		
		
		
g.sort("course_code",SortDirection.ASCENDING);

addComponent(g);
		Button AddCourses = new Button("Add New Course", event ->
		{
			removeAllComponents();
			AddNewCourse();

		});
		Button Delete = new Button("Delete A Course", event ->
		{

			DeleteCourses();

		});
		Button Edit = new Button("Edit A Course", event ->
		{

			EditCourse();
		});

		HorizontalLayout button_row = new HorizontalLayout();
		button_row.addComponent(AddCourses);
		button_row.addComponent(Delete);
		button_row.addComponent(Edit);
		addComponent(button_row);

		// this just goes back to the menu that is first shown
		Button go_back_to_main_view = new Button("Return to menu", event ->
		{
			getUI().getNavigator().navigateTo("adminmain");

		});
		addComponent(go_back_to_main_view);

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
