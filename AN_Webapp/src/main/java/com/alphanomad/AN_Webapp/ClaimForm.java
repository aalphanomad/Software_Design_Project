package com.alphanomad.AN_Webapp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.annotation.WebServlet;

import com.google.gson.JsonObject;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class ClaimForm extends VerticalLayout implements View
{
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

	public Boolean validate()
	{
		Boolean valid = true;
		if (Courses.isEmpty())
		{
			valid = false;
			Courses.setComponentError(new UserError("Please Select A Course."));
		}
		if (Venue.isEmpty())
		{
			valid = false;
			Venue.setComponentError(new UserError("Please Select A Venue."));
		}
		if (Activity.isEmpty())
		{
			valid = false;
			Activity.setComponentError(new UserError("Please Select A Activity."));
		}
		if (startHour.isEmpty())
		{
			valid = false;
			startHour.setComponentError(new UserError("Please Select the Time."));
		}
		if (startMinute.isEmpty())
		{
			valid = false;
			startMinute.setComponentError(new UserError("Please Select the Time."));
		}
		if (endHour.isEmpty())
		{
			valid = false;
			endHour.setComponentError(new UserError("Please Select the Time."));
		}
		if (endMinute.isEmpty())
		{
			valid = false;
			endMinute.setComponentError(new UserError("Please Select the Time."));
		}
		// b.ans.equals("-1") ||
		if (startTime != null && endTime != null)
		{
			if (checktimings(startTime, endTime) == false)
			{
				valid = false;
				startHour.setComponentError(new UserError("Please Select a Valid Time."));
				startMinute.setComponentError(new UserError("Please Select a Valid Time."));
				endHour.setComponentError(new UserError("Please Select a Valid Time."));
				endMinute.setComponentError(new UserError("Please Select a Valid Time."));
			}
		}
		return valid;
	}

	private boolean checktimings(String startTime1, String endTime1)
	{

		String pattern = "HH:mm";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		startTime1 = startTime1.replaceAll("\\s+", "");
		endTime1 = endTime1.replaceAll("\\s+", "");
		try
		{
			Date date1 = sdf.parse(startTime1);
			Date date2 = sdf.parse(endTime1);

			startTime = startTime1;
			endTime = endTime1;
			if (date1.before(date2))
			{
				return true;
			} else
			{

				return false;
			}

		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public static ArrayList<String> GetCourses(String FromDB)
	{

		// Notification.show(ans);
		ArrayList<String> new_courses = new ArrayList<String>();

		String[] courses1 = FromDB.split(",");
		for (int i = 0; i < courses1.length; i++)
		{
			String[] courses2 = courses1[i].split(":");
			if (i == courses1.length - 1)
			{
				if (!courses2[1].substring(0, courses2[1].length() - 2).equals("null"))
				{
					new_courses.add(courses2[1].substring(1, courses2[1].length() - 3));

				}
			} else
			{
				if (!courses2[1].equals("null"))
					new_courses.add(courses2[1].substring(1, courses2[1].length() - 1));
			}
		}
		return new_courses;
	}

	public static String Course_corr(String course_code)
	{
		String ans = null;
		switch (course_code)
		{
		case "COMS1015":
			ans = "(Basic Computer Organisation)";
			break;
		case "COMS1018":
			ans = "(Introduction to Algorithms and Programming)";
			break;
		case "COMS1017":
			ans = "(Introduction to Data Structures and Algorithms)";
			break;
		case "COMS1016":
			ans = "(Discrete Computational Structures)";
			break;
		case "COMS2002":
			ans = "(Database Fundamentals)";
			break;
		case "COMS2013":
			ans = "(Mobile Computing)";
			break;
		case "COMS2014":
			ans = "(Computer Networks)";
			break;
		case "COMS2015":
			ans = "(Analysis of Algorithms)";
			break;
		case "COMS3002":
			ans = "(Software Engineering)";
			break;
		case "COMS3003":
			ans = "(Formal Languages and Automata)";
			break;
		case "COMS3005":
			ans = "(Advanced Analysis of Algorithms)";
			break;
		case "COMS3009":
			ans = "(Software Design)";
			break;
		case "COMS3010":
			ans = "(Operating Systems and System Programming)";
			break;
		case "COMS3007":
			ans = "(Machine Learning)";
			break;
		case "COMS3006":
			ans = "(Computer Graphics and Visualisation)";
			break;
		case "COMS3008":
			ans = "(Parallel Computing)";
			break;
		case "COMS3011":
			ans = "(Software Design Project)";
			break;

		}
		return ans;

	}

	public String[] getAllInfo()
	{
		return AllInfo.clone();
	}

	@SuppressWarnings("unchecked")
	public ClaimForm()
	{

	}

	@Override
	public void enter(ViewChangeEvent event)
	{

		removeAllComponents();
		dbh = new DBHelper();
		UserInfo tutor_info = ((MyUI) getUI()).get_user_info();

		Label test = new Label("<p style = \"font-family:georgia,garamond,serif;font-size:30px;\">\r\n"
				+ "       <b><u>Claim Form</u></b> " + "      </p>", ContentMode.HTML);
		addComponent(test);

		String[] params = { "name", "student_num" };
		String[] values = { tutor_info.name, tutor_info.student_num };

		String ans = dbh.php_request("get_ValidCourses", params, values);
		filtered = dbh.parse_json_string(ans);
		ans = filtered.get("result").getAsJsonArray().toString();

		ArrayList<String> new_courses = GetCourses(ans);
		new_courses.set(0, new_courses.get(0).substring(0, new_courses.get(0).length()-1));

		ArrayList<String> coursesArray = new ArrayList();
		for (int i = 0; i < new_courses.size(); i++)
		{
			coursesArray.add(new_courses.get(i) + " " + Course_corr(new_courses.get(i)));
		}

		ArrayList<String> activityArray = new ArrayList();
		activityArray.add("Tutoring");
		activityArray.add("Invigilating");
		activityArray.add("Marking");
		activityArray.add("Other");

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

		Courses = new ComboBox<String>("Course");
		Courses.setPlaceholder("Please fill in");
		Courses.setWidth("100%");
		Courses.setItems(coursesArray);
		Courses.setValue(coursesArray.get(0));
		addComponent(Courses);

		Venue = new TextField();
		Venue.setPlaceholder("Please fill in");
		Venue.setCaption("Venue");
		addComponent(Venue);

		Activity = new ComboBox<String>("Type of Activity");
		Activity.setPlaceholder("Please fill in");
		Activity.setItems(activityArray);
		addComponent(Activity);

		startHour = new ComboBox<String>("Hour(s)");
		startHour.setPlaceholder("Please fill in");
		startHour.setItems(hour);

		startMinute = new ComboBox<String>("Minutes");
		startMinute.setPlaceholder("Please fill in");
		startMinute.setItems(minute);

		endHour = new ComboBox<String>("Hour(s)");
		endHour.setPlaceholder("Please fill in");
		endHour.setItems(hour);

		endMinute = new ComboBox<String>("Minutes");
		endMinute.setPlaceholder("Please fill in");
		endMinute.setItems(minute);

		HorizontalLayout time1 = new HorizontalLayout();

		Label st = new Label("<p style = \"font-family:georgia,garamond,serif;font-size:20px;\">\r\n"
				+ "       Start-Time " + "      </p>", ContentMode.HTML);

		time1.addComponent(st);
		time1.addComponent(startHour);
		time1.addComponent(startMinute);
		
		addComponent(time1);

		HorizontalLayout time2 = new HorizontalLayout();

		Label ed = new Label("<p style = \"font-family:georgia,garamond,serif;font-size:20px;\">\r\n"
				+ "       End-Time " + "      </p>", ContentMode.HTML);

		time2.addComponent(ed);
		time2.addComponent(endHour);
		time2.addComponent(endMinute);
		
		addComponent(time2);

		Date today = new Date();

		String day = today.toString().substring(8, 10);
		String month = today.toString().substring(4, 7);
		String year = today.toString().substring(24, today.toString().length());

		String date = day + " " + month + " " + year;

		confirm = new Button("Confirm");
		addComponent(confirm);
		confirm.addClickListener(e ->
		{
			Courses.setComponentError(null);
			Venue.setComponentError(null);
			Activity.setComponentError(null);
			startHour.setComponentError(null);
			startMinute.setComponentError(null);
			endHour.setComponentError(null);
			endMinute.setComponentError(null);
			if (validate() == true)
			{
				startTime = startHour.getValue().toString() + ":" + startMinute.getValue().toString();
				endTime = endHour.getValue().toString() + ":" + endMinute.getValue().toString();
				b = new Booking(tutor_info.name, tutor_info.student_num,
						EditString.editCourse((Courses.getValue().toString())), Activity.getValue().toString(),
						Venue.getValue().toString(), date, startTime, endTime);

				if (b.ans.equals("-1"))
				{
					startHour.setComponentError(new UserError("This is a Duplicate Claim."));
					startMinute.setComponentError(new UserError("This is a Duplicate Claim."));
					endHour.setComponentError(new UserError("This is a Duplicate Claim."));
					endMinute.setComponentError(new UserError("This is a Duplicate Claim."));
				} else
				{

					removeAllComponents();

					addComponent(new ConfirmClaimForm(tutor_info.name, tutor_info.student_num,
							EditString.editCourse((Courses.getValue().toString())), Activity.getValue().toString(),
							Venue.getValue().toString(), date, startTime, endTime));

					// navigator.addView(CONFIRMCLAIMFORM, c);
					// addComponent(c);
					// getUI().getNavigator().navigateTo("confirm");
				}
			}
		});
	}

}
