package com.alphanomad.AN_Webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.vaadin.addons.ComboBoxMultiselect;

import com.google.gson.JsonObject;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.View;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Theme("mytheme")
public class Register extends VerticalLayout implements View {

	public void Handle_DB_Interaction(String[] params, String[] values) {
		DBHelper dbh = new DBHelper();
		String ans = dbh.php_request("create", params, values);
		result2 = dbh.parse_json_string(ans);
		ans = result2.get("result").getAsString();
		if (ans.equals("0")) {
			Notification.show("Registration Successful");

			if (radio_group.getValue().equals("Tutor")) {
				((MyUI) getUI()).set_user_info(
						new UserInfo(Name.getValue().toString(), StudentNumber.getValue().toString(), "0"));
				((MyUI) getUI()).logged_in = true;
				getUI().getNavigator().navigateTo("tutormain");
			} else {
				((MyUI) getUI()).set_user_info(
						new UserInfo(Name.getValue().toString(), StudentNumber.getValue().toString(), "1"));
				((MyUI) getUI()).logged_in = true;
				getUI().getNavigator().navigateTo("lectmain");
			}
		} else {
			StudentNumber.setComponentError(new UserError("A Profile With This Student Number Already Exists."));
		}

	}

	public TextField Name, StudentNumber;
	public PasswordField Password, ConfPassword;
	public ComboBoxMultiselect<String> comboBoxMultiselect = new ComboBoxMultiselect<>();
	public FormLayout content = new FormLayout();
	public RadioButtonGroup radio_group;
	JsonObject result1, result2;

	public void TheRegister() {
		removeAllComponents();

		ArrayList<String> FCourses = new ArrayList<String>();
		String[] Courses = comboBoxMultiselect.getValue().toString().split(",");
		ArrayList<String> DummyCourses = new ArrayList<String>();

		Boolean valid = true;
		Name.setComponentError(null);
		StudentNumber.setComponentError(null);
		Password.setComponentError(null);
		ConfPassword.setComponentError(null);
		comboBoxMultiselect.setComponentError(null);
		radio_group.setComponentError(null);

		if (Name.isEmpty()) {
			Name.setComponentError(new UserError("Please Enter Your Name"));
			valid = false;
		}
		if (StudentNumber.isEmpty()) {
			StudentNumber.setComponentError(new UserError("Please Enter Your Student Number"));
			valid = false;

		}
		if (!Password.getValue().equals(ConfPassword.getValue())) {
			ConfPassword.setComponentError(new UserError("Passwords Do Not Match."));
			valid = false;

		}
		if (Password.isEmpty()) {
			Password.setComponentError(new UserError("Please Enter Your Password"));
			valid = false;

		}
		if (ConfPassword.isEmpty()) {
			ConfPassword.setComponentError(new UserError("Please Confirm Your Password"));
			valid = false;

		}
		if (Arrays.toString(Courses).equals("[[]]"))// FIX THIS! NOT CHECKING PROPERLY IF NO COURSE IS SELECED!
		{
			comboBoxMultiselect.setComponentError(new UserError("Please Select Atleast One Course"));
			valid = false;
		}

		if (valid) {

			for (int i = 0; i < Courses.length; i++) {
				String[] newCourses = Courses[i].split("-");
				DummyCourses.add(newCourses[0].toString());
			}
			DummyCourses.set(0, DummyCourses.get(0).substring(1, DummyCourses.get(0).length()));
			String email = StudentNumber.getValue().toString() + "@students.wits.ac.za";

			String[] values = new String[DummyCourses.size() + 5];
			values[0] = Name.getValue().toString();
			values[1] = StudentNumber.getValue().toString();
			values[2] = email;
			values[3] = Password.getValue().toString();

			for (int i = 4; i < 4 + DummyCourses.size(); i++) {
				if (i != 4)
					values[i] = DummyCourses.get(i - 4).substring(1);
				else {
					values[i] = DummyCourses.get(i - 4);

				}
			}
			if (radio_group.getValue().equals("Tutor"))
				values[3 + DummyCourses.size() + 1] = "0";
			else {
				values[3 + DummyCourses.size() + 1] = "1";
			}

			if (DummyCourses.size() == 1) {
				String[] params = { "name", "student_num", "email", "password", "course1", "role" };
				Handle_DB_Interaction(params, values);

			} else if (DummyCourses.size() == 2) {
				String[] params = { "name", "student_num", "email", "password", "course1", "course2", "role" };
				Handle_DB_Interaction(params, values);

			} else if (DummyCourses.size() == 3) {
				String[] params = { "name", "student_num", "email", "password", "course1", "course2", "course3",
						"role" };
				Handle_DB_Interaction(params, values);

			} else if (DummyCourses.size() == 4) {
				String[] params = { "name", "student_num", "email", "password", "course1", "course2", "course3",
						"course4", "role" };
				Handle_DB_Interaction(params, values);

			} else {
				String[] params = { "name", "student_num", "email", "password", "course1", "course2", "course3",
						"course4", "course5", "role" };
				Handle_DB_Interaction(params, values);

			}
		}

	}

	@SuppressWarnings("unchecked")
	public Register() {
		// Clear the form when we return!!!!!!
		Panel panel = new Panel();
		panel.setHeight("600px");
		panel.setWidthUndefined();
		addComponent(panel);
		setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

		content.addStyleName("Template");
		content.setMargin(true);

		Label test = new Label("<p style = \"font-family:georgia,garamond,serif;font-size:30px;\">\r\n"
				+ "       <b><u>Register</u></b> " + "      </p>", ContentMode.HTML);
		content.addComponent(test);
		content.setComponentAlignment(test, Alignment.TOP_CENTER);

		Name = new TextField();
		Name.setCaption("Name");
		Name.setWidth("330px");
		Name.setPlaceholder("Name");
		content.addComponent(Name);
		content.setComponentAlignment(Name, Alignment.MIDDLE_CENTER);

		StudentNumber = new TextField();
		StudentNumber.setCaption("Student Number");
		StudentNumber.setWidth("330px");
		StudentNumber.setPlaceholder("Student Number");
		content.addComponent(StudentNumber);
		content.setComponentAlignment(StudentNumber, Alignment.MIDDLE_CENTER);

		Password = new PasswordField();
		Password.setCaption("Password");
		Password.setWidth("330px");
		Password.setPlaceholder("Password");
		content.addComponent(Password);
		content.setComponentAlignment(Password, Alignment.MIDDLE_CENTER);

		ConfPassword = new PasswordField();
		ConfPassword.setCaption("Confirm Password");
		ConfPassword.setWidth("330px");
		ConfPassword.setPlaceholder("Confirm Password");
		content.addComponent(ConfPassword);
		content.setComponentAlignment(ConfPassword, Alignment.MIDDLE_CENTER);

		CheckBox PMA = new CheckBox("Are you on Postgraduate Merit Award(PMA)?", false);
		PMA.addValueChangeListener(event -> PMA.setValue(!PMA.getValue()));
		content.addComponent(PMA);
		content.setComponentAlignment(PMA, Alignment.BOTTOM_LEFT);

		// FILE UPLOAD!!!!!
		// Initialize a list with items
		List<String> list = new ArrayList<String>();
		DBHelper dbh = new DBHelper();
		String ans = dbh.php_request("get_all_courses", new String[] { "" }, new String[] { "" });
		result1 = dbh.parse_json_string(ans);
		String SAll_Course_Name = result1.get("course_name").toString()
				.substring(1, result1.get("course_name").toString().length() - 1).replace("\"", "");
		String SAll_Course_Code = result1.get("course_code").toString()
				.substring(1, result1.get("course_code").toString().length() - 1).replace("\"", "");
		String[] The_Course_Names = SAll_Course_Name.split(",");
		String[] The_Course_Codes = SAll_Course_Code.split(",");
		for (int i = 0; i < The_Course_Names.length; i++) {
			list.add(The_Course_Codes[i] + "-(" + The_Course_Names[i] + ")");
		}

		radio_group = new RadioButtonGroup();
		ArrayList<String> items = new ArrayList<>();
		items.add("Tutor");
		items.add("Lecturer");
		radio_group.setItems(items);
		radio_group.setValue(items.get(0));
		radio_group.addValueChangeListener(event -> {
			if (radio_group.getValue().equals("Tutor")) {
				comboBoxMultiselect.setCaption("Please Select The Courses You Would Like to Tutor(Max. 5)");
			}
			if (radio_group.getValue().equals("Lecturer")) {
				comboBoxMultiselect.setCaption("Please Select The Courses You Will Be Responsiible For.");

			}
		});
		// make the radio group display horizontally
		radio_group.setCaption("Are you a: ");
		content.addComponent(radio_group);
		content.setComponentAlignment(radio_group, Alignment.MIDDLE_CENTER);
		// Initialize the ComboBoxMultiselect
		comboBoxMultiselect.setCaption("Please Select The Courses You Would Like to Tutor(Max. 5\"");
		comboBoxMultiselect.setPlaceholder("Courses");
		comboBoxMultiselect.setWidth("430px");
		comboBoxMultiselect.setItems(list);
		content.addComponent(comboBoxMultiselect);

		panel.setContent(content);

		Button button = new Button("Register", event -> TheRegister());// Must add to Both Tables NOW!!!
		content.addComponent(button);
	}

}