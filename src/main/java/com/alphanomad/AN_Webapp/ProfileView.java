package com.alphanomad.AN_Webapp;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This view is the view which shows the profile it shows the users name and
 * email and all subjects they are linked to
 */
public class ProfileView extends VerticalLayout implements View {
	UserInfo test;
	public static UserInfo user;

	/**
	 * make a user profile view for the current user
	 * 
	 * @param ui Provided by vaadin.
	 */
	public ProfileView(MyUI ui) {

	}

	/**
	 * Make a profile view for a specific user
	 * 
	 * @param ui        Provided by vaadin
	 * @param user_info a UserInfo object for the user you want to view
	 */
	public ProfileView(MyUI ui, UserInfo user_info) {
		
	}

	/**
	 * Make a profile view for a specific user
	 * 
	 * @param ui        Provided by vaadin
	 * @param user_info a UserInfo object for the user you want to view
	 */
	public ProfileView(MyUI ui, String student_num) {
		
	}

	/*@Override
	public void enter(ViewChangeEvent event) {

		test = ((MyUI) getUI()).get_user_info();
		this.removeAllComponents();

		String stud_num = "";
		MyUI ui = (MyUI) getUI();

		if (ui.get_user_info().get_role().equals("0")) {
			this.user = ui.get_user_info();
		}
		*/

	/**
	 * simple function to make a view that shows the users email and name
	 * 
	 * @param name
	 * @param student_number
	 * @return a Vertical layout to be added to a page
	 */
	public Panel make_user_info_panel(String name, String student_number) {
		Panel panel = new Panel();
		HorizontalLayout inner = new HorizontalLayout();
		inner.setMargin(true);

		// User profile images are created based on their names	
		

		
		return panel;
	}

	/**
	 * 
	 * @param courses_obj a Json Object
	 * @return a vertical layout containing a collapseable list of courses that the
	 *         user is linked to
	 */
	/*public Panel make_courses_panel(JsonArray courses_obj) {
		Panel panel = new Panel();
		VerticalLayout courses_inner = new VerticalLayout();
		List<String> list = new ArrayList<String>();
		for(CourseItem item: get_all_courses())
		{
			list.add(item.getCourse_code());
		}

		ComboBoxMultiselect<String> course_combo_box = new ComboBoxMultiselect<>();
		// Initialize the ComboBoxMultiselect
		course_combo_box.setPlaceholder("Courses");
		course_combo_box.setWidth("430px");
		course_combo_box.setCaption("Please Select The Courses You Would Like to Tutor(Max. 5");
		course_combo_box.setItems(list);
		course_combo_box.setVisible(false);

		*/

	public boolean handle_course_change(ArrayList<String> courses, String stud_num) {
//		boolean result = true;
//		for (String course : courses) {
//			update_courses(stud_num, course, "");
//		}
//
//		return result;
		return true;
	}

	/**
	 * 
	 * @param stud_num
	 * @param course
	 * @param confirmed
	 * @return
	 */
	public boolean update_courses(String stud_num, String course, String confirmed) {
//		boolean result = false;
//		DBHelper dbh = new DBHelper();
//		String value;
//
//		if (confirmed.length() > 0) {
//			String[] params = { "student_num", "course", "confirmed" };
//			String[] values = { stud_num, course, confirmed };
//			value = dbh.php_request("update_courses", params, values);
//		} else {
//			String[] params = { "student_num", "course" };
//			String[] values = { stud_num, course };
//			value = dbh.php_request("update_courses", params, values);
//		}
//
//		result = Boolean.parseBoolean(value);
//
//		return result;
		return false;
	}

	/*ArrayList<CourseItem> get_all_courses()
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

	}*/
}

