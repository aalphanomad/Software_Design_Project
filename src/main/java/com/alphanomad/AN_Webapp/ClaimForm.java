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

		// fill arraylist with the courses fetched from the database
		ArrayList<String> new_courses = GetCourses(ans);

		ArrayList<String> coursesArray = new ArrayList();
		for (int i = 0; i < new_courses.size(); i++) {
			coursesArray.add(new_courses.get(i) + " " + Course_corr(new_courses.get(i)));
		}

		// arraylist to store different types of work the tutor can do
		ArrayList<String> activityArray = new ArrayList();
		activityArray.add("Tutoring");
		activityArray.add("Invigilating");
		activityArray.add("Marking");
		activityArray.add("Other");

		// the 2 arralists below are used to store values for our own custom made time
		// picker
		ArrayList<String> hour = new ArrayList();
		hour.add("00");
		hour.add("01");
		hour.add("02");
		hour.add("03");
		hour.add("04");
		hour.add("05");
		hour.add("06");
		hour.add("07");
		hour.add("08");
		hour.add("09");
		hour.add("10");
		hour.add("11");
		hour.add("12");
		hour.add("13");
		hour.add("14");
		hour.add("15");
		hour.add("16");
		hour.add("17");
		hour.add("18");
		hour.add("19");
		hour.add("20");
		hour.add("21");
		hour.add("22");
		hour.add("23");

		ArrayList<String> minute = new ArrayList();
		minute.add("00");
		minute.add("05");
		minute.add("10");
		minute.add("15");
		minute.add("20");
		minute.add("25");
		minute.add("30");
		minute.add("35");
		minute.add("40");
		minute.add("45");
		minute.add("50");
		minute.add("55");
		minute.add("60");

		// Creates the drop down menu containing the courses pertaining to the tutor
		Courses = new ComboBox<String>("Course");
		Courses.setPlaceholder("Please fill in");
		Courses.setWidth("100%");
		Courses.setItems(coursesArray);
		Courses.setValue(coursesArray.get(0));
		addComponent(Courses);

		/// Creates the Textfield where the tutor enters the venue where they have
		/// tutored
		Venue = new TextField();
		Venue.setPlaceholder("Please fill in");
		Venue.setCaption("Venue");
		addComponent(Venue);

		// Creates the textfield where the tutor will enter the activity they have
		// performed
		Activity = new ComboBox<String>("Type of Activity");
		Activity.setPlaceholder("Please fill in");
		Activity.setItems(activityArray);
		addComponent(Activity);

		// Creates the field where the user enters the start hour
		startHour = new ComboBox<String>("Hour(s)");
		startHour.setPlaceholder("Please fill in");
		startHour.setItems(hour);

		// Creates the field where the user enters the start minute
		startMinute = new ComboBox<String>("Minutes");
		startMinute.setPlaceholder("Please fill in");
		startMinute.setItems(minute);

		// Creates the field where the user enters the end hour

		endHour = new ComboBox<String>("Hour(s)");
		endHour.setPlaceholder("Please fill in");
		endHour.setItems(hour);

		// Creates the field where the user enters the end minute

		endMinute = new ComboBox<String>("Minutes");
		endMinute.setPlaceholder("Please fill in");

	*/

}
