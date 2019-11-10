package com.alphanomad.AN_Webapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.UserError;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ClaimForm extends VerticalLayout implements View {
	ComboBox<String> Courses;
	TextField Venue;
	ComboBox<String> Activity;
	ComboBox<String> startHour;
	ComboBox<String> startMinute;
	ComboBox<String> endHour;
	ComboBox<String> endMinute;
	Button confirm;

	String[] AllInfo;
	static JsonObject filtered;
	static DBHelper dbh;

	Booking b;
	String startTime, endTime;

	// The function below validates the textfields to ensure that none of the fields
	// are empty
	public Boolean validate() {
		Boolean valid = true;
		return valid;
	}

	// This function ensures that the times entered are valiid. More specifically
	// it checks whether the end time is before the start time
	public boolean checktimings(String startTime1, String endTime1) {

		String pattern = "HH:mm";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		startTime1 = startTime1.replaceAll("\\s+", "");
		endTime1 = endTime1.replaceAll("\\s+", "");
		try {
			Date date1 = sdf.parse(startTime1);
			Date date2 = sdf.parse(endTime1);

			startTime = startTime1;
			endTime = endTime1;
			if (date1.before(date2)) {
				return true;
			} else {

				return false;
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	// This populates a drop down menu with the courses that the tutor is assigned
	public static ArrayList<String> GetCourses(String FromDB) {

		// Notification.show(ans);
		ArrayList<String> new_courses = new ArrayList<String>();

		String[] courses1 = FromDB.split(",");
		for (int i = 0; i < courses1.length; i++) {
			String[] courses2 = courses1[i].split(":");
			if (i == courses1.length - 1) {
				if (!courses2[1].substring(0, courses2[1].length() - 2).equals("null")) {
					new_courses.add(courses2[1].substring(1, courses2[1].length() - 3));

				}
			} else {
				if (!courses2[1].equals("null"))
					new_courses.add(courses2[1].substring(1, courses2[1].length() - 2));
			}
		}
		return new_courses;
	}

	public static String Course_corr(String course_code) {

		DBHelper dbh = new DBHelper();
		String[] parameters = { "table", "target", "filter", "value" };
		String[] values = { "COURSES", "COURSE_NAME", "COURSE_CODE", course_code };

		String test = dbh.php_request("generic_select", parameters, values);
		JsonArray course_name;

		course_name = dbh.parse_json_string_arr(test);

		String ans = "(" + course_name.get(0).getAsString() + ")";

		return ans;

	}

	public String[] getAllInfo() {
		return AllInfo.clone();
	}

	@SuppressWarnings("unchecked")
	public ClaimForm() {

	}

	/*@Override
	public void enter(ViewChangeEvent event) {

		removeAllComponents();
		dbh = new DBHelper();
		UserInfo tutor_info = ((MyUI) getUI()).get_user_info();
		// set up heading for UI enhancement
		Label test = new Label("<p style = \"font-family:georgia,garamond,serif;font-size:30px;\">\r\n"
				+ "       <b><u>Claim Form</u></b> " + "      </p>", ContentMode.HTML);
		addComponent(test);

		// following action uses the tutor's name and student number to get the
		// validated courses permitted for him/her
		String[] params = { "name", "student_num" };
		String[] values = { tutor_info.name, tutor_info.student_num };

		String ans = dbh.php_request("get_ValidCourses", params, values);
		filtered = dbh.parse_json_string(ans);
		ans = filtered.get("result").getAsJsonArray().toString();


	*/

}
