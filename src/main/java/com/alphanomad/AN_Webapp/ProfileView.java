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
	public ProfileView(MyUI ui, String student_num) {
		this.user = new UserInfo(student_num);
		System.out.println("STRING PROF VIEW " + this.user.get_student_num());
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
		ExternalResource resource;
		try {
			resource = new ExternalResource("https://ui-avatars.com/api/?size=128&background=003B5C&color=FFFFFF&name="
					+ URLEncoder.encode(name, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			resource = new ExternalResource("https://sophosnews.files.wordpress.com/2014/04/anonymous-250.jpg?w=100");
			e.printStackTrace();
		}
		Image image = new Image("Profile Image");

		image.setSource(resource);
		image.setHeight("100px");

		inner.addComponent(image);

		VerticalLayout details = new VerticalLayout();

		HorizontalLayout stud_num_line = new HorizontalLayout();
		stud_num_line.addComponent(new Label("Student Number:"));
		stud_num_line.addComponent(new Label(student_number));

		HorizontalLayout name_line = new HorizontalLayout();
		name_line.addComponent(new Label("Name:"));
		name_line.addComponent(new Label(name));

		HorizontalLayout email_line = new HorizontalLayout();
		String[] parameters = { "table", "target", "filter", "value" };
		String[] values1 = { "USER_INFORMATION", "EMAIL_ADDRESS", "STUDENT_NUM", student_number };
		DBHelper dbh = new DBHelper();
		String email_result = dbh.php_request("generic_select", parameters, values1);
		String email_addr;
		if (!email_result.startsWith("<br")) {
			email_addr = dbh.parse_json_string_arr(email_result).get(0).getAsString();
		} else {
			email_addr = "Unknown";
		}
		Button email_button = new Button("Email User",
				event -> UI.getCurrent().getPage().open("mailto:" + email_addr, "_blank"));
		email_line.addComponent(new Label("Email Address:"));
		email_line.addComponent(new Label(email_addr));

		MyUI ui = (MyUI) getUI();
		try
		{
			if (!ui.get_user_info().role.equals("0") && !email_addr.endsWith(".com")) {
				email_line.addComponent(email_button);
			}
		}
		catch(Exception e)
		{
			//TODO
		}
		

		HorizontalLayout transcript_line = new HorizontalLayout();
		transcript_line.addComponent(new Label("View Transcript:"));
		String[] values2 = { "USER_INFORMATION", "user_transcript", "user_id", student_number };
		Button pdf_button = new Button("View Transcript", event -> {
			String result = dbh.php_request("generic_select", parameters, values2);

			if (!result.startsWith("<br")) {
				result = dbh.parse_json_string_arr(result).get(0).getAsString();
				// Notification.show(result);
				UI.getCurrent().getPage().open(result, "_blank");

			} else {
				Notification.show("No transcript associated with this user");
			}

		});
		transcript_line.addComponent(pdf_button);

		details.addComponent(stud_num_line);
		details.addComponent(name_line);
		details.addComponent(email_line);
		details.addComponent(transcript_line);

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

