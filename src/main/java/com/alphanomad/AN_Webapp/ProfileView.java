package com.alphanomad.AN_Webapp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.vaadin.addons.ComboBoxMultiselect;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This view is the view which shows the profile it shows the users name and
 * email and all subjects they are linked to
 */
public class ProfileView extends VerticalLayout implements View
{
	UserInfo test;
	public static UserInfo user;

	/**
	 * make a user profile view for the current user
	 * 
	 * @param ui Provided by vaadin.
	 */
	public ProfileView(MyUI ui)
	{

	}

	/**
	 * Make a profile view for a specific user
	 * 
	 * @param ui        Provided by vaadin
	 * @param user_info a UserInfo object for the user you want to view
	 */
	public ProfileView(MyUI ui, UserInfo user_info)
	{
		System.out.println("PROFILEVIEW " + user_info.get_student_num());
		this.user = user_info.copy();
		System.out.println("PROFILEVIEW COPY" + this.user.get_student_num());
	}

	/**
	 * Make a profile view for a specific user
	 * 
	 * @param ui        Provided by vaadin
	 * @param user_info a UserInfo object for the user you want to view
	 */
	public ProfileView(MyUI ui, String student_num)
	{
		this.user = new UserInfo(student_num);
		System.out.println("STRING PROF VIEW " + this.user.get_student_num());
	}

	@Override
	public void enter(ViewChangeEvent event)
	{

		test = ((MyUI) getUI()).get_user_info();
		System.out.println("HELLLLLLLLO" + test.student_num);
		this.removeAllComponents();

		String stud_num = "";
		MyUI ui = (MyUI) getUI();

		if (this.user == null)
		{
			this.user = ui.get_user_info();
		}

		System.out.println("HELLO" + this.user.get_student_num());

		stud_num = user.get_student_num();
		String role = user.get_role();

		DBHelper dbh = new DBHelper();

		String[] params = { "student_num" };
		String[] values = { stud_num };
		String name = dbh.php_request("get_name", params, values);
		System.out.println(name + " " + stud_num);

		String[] parameters = { "table", "target", "filter", "value" };
		String[] values2 = { "USER_COURSE_ALLOC", "COURSE,CONFIRMED", "STUDENT_NUM", stud_num };
		String courses = dbh.php_request("generic_select", parameters, values2);
		JsonArray courses_obj = new JsonArray();
		try
		{
			courses_obj = dbh.parse_json_string_arr(courses);
		} catch (Exception e)
		{
			System.out.println("HERE\n HERE\n HERE\n");
			System.out.println(e);
		}

		Button home_button = new Button("go to main view", btn_event -> {
			if(((MyUI)(getUI())).get_user_info().get_role().equals("1"))
			{
				getUI().getNavigator().navigateTo("lectmain");	
			}
			else if(((MyUI)(getUI())).get_user_info().get_role().equals("2"))
			{
				getUI().getNavigator().navigateTo("adminmain");
			}
			else
			{
				// this should never happen
				// but it's always good to be safe
				getUI().getNavigator().navigateTo("login");
			}
			
		});

		addComponent(make_user_info_panel(name, stud_num));
		addComponent(make_courses_panel(courses_obj));
		addComponent(home_button);
	}

	/**
	 * simple function to make a view that shows the users email and name
	 * 
	 * @param name
	 * @param student_number
	 * @return a Vertical layout to be added to a page
	 */
	public Panel make_user_info_panel(String name, String student_number)
	{
		Panel panel = new Panel();
		HorizontalLayout inner = new HorizontalLayout();
		inner.setMargin(true);

		ExternalResource resource = new ExternalResource(
				"https://sophosnews.files.wordpress.com/2014/04/anonymous-250.jpg?w=100");
		Image image = new Image("Profile Image");

		image.setSource(resource);
		image.setHeight("100px");

		inner.addComponent(image);

		VerticalLayout details = new VerticalLayout();

		HorizontalLayout stud_num_line = new HorizontalLayout();
		stud_num_line.addComponent(new Label("Student Number:"));
		stud_num_line.addComponent(new Label(student_number));

		HorizontalLayout email_line = new HorizontalLayout();
		email_line.addComponent(new Label("Email Address:"));
		email_line.addComponent(new Label(student_number + "@students.wits.ac.za"));

		details.addComponent(stud_num_line);
		details.addComponent(email_line);

		inner.addComponent(details);

		panel.setCaption("User Information");
		panel.setContent(inner);
		return panel;
	}

	/**
	 * 
	 * @param courses_obj a Json Object
	 * @return a vertical layout containing a collapseable list of courses that the
	 *         user is linked to
	 */
	public Panel make_courses_panel(JsonArray courses_obj)
	{
		Panel panel = new Panel();
		VerticalLayout courses_inner = new VerticalLayout();
		try
		{
			for (int x = 0; x < courses_obj.size(); x++)
			{
				try
				{
					String course_name = ((JsonArray) courses_obj.get(x)).get(0).getAsString();
					String course_conf = ((JsonArray) courses_obj.get(x)).get(1).getAsString();
					if(course_conf.equals("1"))
					{
						courses_inner.addComponent(new Label( course_name + "\n"));
					}
					else
					{
						courses_inner.addComponent(new Label( course_name + " (Pending confirmation) \n"));
					}
					
				} catch (UnsupportedOperationException e)
				{
					//System.out.println("HERE\n HERE\n HERE\n");
				}
			}
			

		} catch (Exception e)
		{
			e.printStackTrace();
			courses_inner.addComponent(new Label("There's nothing here... \n"));
		}

		List<String> list = new ArrayList<String>();
		list.add("COMS1016-(Discrete Computational Structures)");
		list.add("COMS2002-(Database Fundementals)");
		list.add("COMS2013-(Mobile Computing)");
		list.add("COMS2014-(Computer Networks)");
		list.add("COMS2015-(Analysis of Algorithms)");
		list.add("COMS3003-(Formal Languages and Automata)");
		list.add("COMS3005-(Advanced Analysis of Algorithms)");
		list.add("COMS3009-(Software Design)");
		list.add("COMS3010-(Operating Systems & System Programming)");
		list.add("COMS3007-(Machine Learning)");
		list.add("COMS3006-(Computer Graphics & Vis.)");
		list.add("COMS3008-Parallel Computing)");
		list.add("COMS3011-(Software Design Project)");

		ComboBoxMultiselect<String> course_combo_box = new ComboBoxMultiselect<>();
		// Initialize the ComboBoxMultiselect
		course_combo_box.setPlaceholder("Courses");
		course_combo_box.setWidth("430px");
		course_combo_box.setCaption("Please Select The Courses You Would Like to Tutor(Max. 5");
		course_combo_box.setItems(list);
		course_combo_box.setVisible(false);
		if (test.role.equals("0"))
		{
			Button change_courses = new Button("Change courses", event -> course_combo_box.setVisible(true));

			change_courses.addClickListener(event -> change_courses.setVisible(false));
		}
		/*
		 * Button done = new Button("Done", event -> change_courses.setVisible(true));
		 * done.addClickListener(event -> done.setVisible(false));
		 * done.addClickListener(event -> course_combo_box.setVisible(false));
		 * done.setVisible(false);
		 * 
		 * 
		 * 
		 * change_courses.addClickListener(event -> done.setVisible(true));
		 * 
		 * courses_inner.addComponent(change_courses);
		 * courses_inner.addComponent(course_combo_box);
		 * courses_inner.addComponent(done);
		 */

		panel.setCaption("Courses");
		panel.setContent(courses_inner);

		return panel;
	}

	public boolean handle_course_change(ArrayList<String> courses, String stud_num)
	{
		boolean result = true;
		for (String course : courses)
		{
			update_courses(stud_num, course, "");
		}

		return result;
	}

	/**
	 * 
	 * @param stud_num
	 * @param course
	 * @param confirmed
	 * @return
	 */
	public boolean update_courses(String stud_num, String course, String confirmed)
	{
		boolean result = false;
		DBHelper dbh = new DBHelper();
		String value;

		if (confirmed.length() > 0)
		{
			String[] params = { "student_num", "course", "confirmed" };
			String[] values = { stud_num, course, confirmed };
			value = dbh.php_request("update_courses", params, values);
		} else
		{
			String[] params = { "student_num", "course" };
			String[] values = { stud_num, course };
			value = dbh.php_request("update_courses", params, values);
		}

		result = Boolean.parseBoolean(value);

		return result;
	}
}