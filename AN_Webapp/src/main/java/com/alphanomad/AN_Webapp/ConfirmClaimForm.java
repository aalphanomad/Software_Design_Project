package com.alphanomad.AN_Webapp;

import javax.servlet.annotation.WebServlet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class ConfirmClaimForm extends VerticalLayout implements View
{
	public static String name, studnum, course, activity, date, venue, startTime, endTime;
	public static String[] array = new String[8];

	String ans1 = null;
	String ans2 = null;

	public ConfirmClaimForm(String str1, String str2, String str3, String str4, String str5, String str6, String str7,
			String str8)
	{

		removeAllComponents();

		Panel panel = new Panel();
		panel.setHeight("500px");
		panel.setWidthUndefined();
		addComponent(panel);
		setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

		FormLayout content = new FormLayout();
		content.addStyleName("Template");
		content.setMargin(true);
		Label test = new Label("<p style = \"font-family:georgia,garamond,serif;font-size:30px;\">\r\n"
				+ "       <b><u>Confirm Your Claim</u></b> " + "      </p>", ContentMode.HTML);
		content.addComponent(test);

		name = str1;
		studnum = str2;
		course = str3;
		activity = str4;
		venue = str5;
		date = str6;
		startTime = str7;
		endTime = str8;

		array[0] = name;
		array[1] = studnum;
		array[2] = date;
		array[3] = course;
		array[4] = venue;
		array[5] = startTime;
		array[6] = endTime;
		array[7] = activity;

		Label label1 = new Label();
		label1.setCaption("Name: " + name);
		content.addComponent(label1);

		Label label2 = new Label();
		label2.setCaption("Student No: " + studnum);
		content.addComponent(label2);

		Label label3 = new Label();
		label3.setCaption("Course Tutored: " + course);
		content.addComponent(label3);

		Label label4 = new Label();
		label4.setCaption("Type of Activity: " + activity);
		content.addComponent(label4);

		Label label5 = new Label();
		label5.setCaption("Venue: " + venue);
		content.addComponent(label5);

		Label label6 = new Label();
		label6.setCaption("Date: " + date);
		content.addComponent(label6);

		Label label7 = new Label();
		label7.setCaption("Time: " + startTime + " - " + endTime);
		content.addComponent(label7);

		String ok = name + studnum;
		String qr = name + "," + studnum + "," + course + "," + activity + "," + venue + "," + date + "," + startTime
				+ "," + endTime;
		Image sample = new Image();
		sample.setSource(
				new ExternalResource("http://api.qrserver.com/v1/create-qr-code/?data=" + qr + "&size=200x200"));

		Button generate = new Button("Generate", event -> addComponent(sample));
		addComponent(generate);

		Button home_button = new Button("Home", event -> getUI().getNavigator().navigateTo("tutormain"));
		addComponent(home_button);

		TextField LecturerUsername = new TextField();
		LecturerUsername.setIcon(VaadinIcons.USER);
		LecturerUsername.setCaption("Lecturer Username");
		LecturerUsername.setPlaceholder("Username");
		addComponent(LecturerUsername);

		TextField LecturerPassword = new PasswordField();
		LecturerPassword.setCaption("Lecturer Password");
		LecturerPassword.setIcon(VaadinIcons.PASSWORD);
		LecturerPassword.setPlaceholder("Password");
		addComponent(LecturerPassword);

		DBHelper dbh1 = new DBHelper();
		String[] params1 = { "student_num", "password" };

		Button validate = new Button("Validate", event ->
		{
			String[] values1 = { LecturerUsername.getValue(), LecturerPassword.getValue() };
			ans1 = dbh1.php_request("is_lecturer", params1, values1);

			String[] part1 = ans1.split(":");
			String FinalAns = part1[1].substring(1, 2);

			if (FinalAns.equals("0"))
			{
				String[] params2 = { "name", "student_num", "course", "date", "venue" };
				String[] values2 = { name, studnum, course, date, venue };
				ans2 = dbh1.php_request("verify", params2, values2);
				Notification.show("Claim Has Been Verified Successfully");
				getUI().getNavigator().navigateTo("tutormain");

			}

			else
			{
				LecturerUsername.setComponentError(new UserError("Only Lecturers are permitted to validate."));
				LecturerPassword.setComponentError(new UserError("Only Lecturers are permitted to validate."));

			}
		});
		addComponent(validate);

		/*
		 * for(int i=0; i<array.length; i++) { System.out.println(array[i]); }
		 */

		panel.setContent(content);
	}

	public static String getName()
	{
		return name;
	}

	public static String getStud()
	{
		return studnum;
	}

	public static String getCourse()
	{
		return course;
	}

	public static String getActivity()
	{
		return activity;
	}

	public static String getDate()
	{
		return date;
	}

	public static String getVenue()
	{
		return venue;
	}

	public static String getStart()
	{
		return startTime;
	}

	public static String getEnd()
	{
		return endTime;
	}

}
