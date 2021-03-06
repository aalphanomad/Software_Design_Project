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
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Theme("mytheme")
public class Register extends VerticalLayout implements View
{

	public TextField Name, StudentNumber;
	public PasswordField Password, ConfPassword;
	public ComboBoxMultiselect<String> comboBoxMultiselect = new ComboBoxMultiselect<>();
	public FormLayout content = new FormLayout();
	JsonObject result;

	public void TheRegister()
	{
		ArrayList<String> FCourses = new ArrayList<String>();
		String[] Courses = comboBoxMultiselect.getValue().toString().split(",");
		ArrayList<String> DummyCourses = new ArrayList<String>();

		Boolean valid = true;
		Name.setComponentError(null);
		StudentNumber.setComponentError(null);
		Password.setComponentError(null);
		;
		ConfPassword.setComponentError(null);

		if (Name.isEmpty())
		{
			Name.setComponentError(new UserError("Please Enter Your Name"));
			valid = false;
		}
		if (StudentNumber.isEmpty())
		{
			StudentNumber.setComponentError(new UserError("Please Enter Your Student Number"));
			valid = false;

		}
		if (!Password.getValue().equals(ConfPassword.getValue()))
		{
			ConfPassword.setComponentError(new UserError("Passwords Do Not Match."));
			valid = false;

		}
		if (Password.isEmpty())
		{
			Password.setComponentError(new UserError("Please Enter Your Password"));
			valid = false;

		}
		if (ConfPassword.isEmpty())
		{
			ConfPassword.setComponentError(new UserError("Please Confirm Your Password"));
			valid = false;

		}
		if (Courses.length == 1)
		{
			comboBoxMultiselect.setComponentError(new UserError("Please Select Atleast One Course"));
			valid = false;
		}
		if (valid)
		{


		}

	}

	public Register()
	{

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
		Button load = new Button("Upload Tanscript",
				event->getUI().getPage().open("http://lamp.ms.wits.ac.za/~s1601745/upload_transcript.html", "_blank") 
	    
				);
		
		content.addComponent(load);
		
		// Initialize a list with items
		List<String> list = new ArrayList<String>();
		DBHelper dbh=new  DBHelper();
		String ans=dbh.php_request("get_all_courses",new String[]{""},new String[] {""});
		result=dbh.parse_json_string(ans);
		String SAll_Course_Name=result.get("course_name").toString().substring(1, result.get("course_name").toString().length()-1).replace("\"", "");
		String SAll_Course_Code=result.get("course_code").toString().substring(1,result.get("course_code").toString().length()-1).replace("\"", "");
		String[] The_Course_Names=SAll_Course_Name.split(",");
		String[] The_Course_Codes=SAll_Course_Code.split(",");
		for(int i=0;i<The_Course_Names.length;i++) {
			list.add(The_Course_Codes[i]+"-("+The_Course_Names[i]+")");
		}

		// Initialize the ComboBoxMultiselect
		comboBoxMultiselect.setPlaceholder("Courses");
		comboBoxMultiselect.setWidth("430px");
		comboBoxMultiselect.setCaption("Please Select The Courses You Would Like to Tutor(Max. 5");
		comboBoxMultiselect.setItems(list);
		content.addComponent(comboBoxMultiselect);
		panel.setContent(content);
		Button button = new Button("Register", event -> TheRegister());

		content.addComponent(button);
	}

}