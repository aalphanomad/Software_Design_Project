package com.alphanomad.AN_Webapp;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.vaadin.addons.ComboBoxMultiselect;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This view is the view which shows the profile it shows the users name and
 * email and all subjects they are linked to
 */
public class ProfileView extends VerticalLayout implements View {
	UserInfo test;
	 	Panel p = new Panel();
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

	@Override
	public void enter(ViewChangeEvent event) {
	
		//the info for test in this class will be for the user viewing someone else's profile. (e.g. admin or lecturer)
		test = ((MyUI) getUI()).get_user_info();
		this.removeAllComponents();

		//ui will refer to the info of the profile being viewed
		String stud_num = "";
		MyUI ui = (MyUI) getUI();

 		if (ui.get_user_info().get_role().equals("0")) {
			this.user = ui.get_user_info();
		}


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
		try {
			courses_obj = dbh.parse_json_string_arr(courses);
		} catch (Exception e) {
			System.out.println(e);
		}

		Button home_button = new Button("go to main view", btn_event -> {
			if (((MyUI) (getUI())).get_user_info().get_role().equals("1")) {
				getUI().getNavigator().navigateTo("lectmain");
			} else if (((MyUI) (getUI())).get_user_info().get_role().equals("2") || ((MyUI) (getUI())).get_user_info().get_role().equals("4")) {
				getUI().getNavigator().navigateTo("adminmain");
			} else if (((MyUI) (getUI())).get_user_info().get_role().equals("0")) {
				getUI().getNavigator().navigateTo("tutormain");
			} else {
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
		Label text=new Label("View Transcript:");
		transcript_line.addComponent(text);
		String[] values2 = { "USER_INFORMATION", "TRANSCRIPT", "STUDENT_NUM", student_number};
		Button pdf_button = new Button("View Transcript", event -> {
			String result = dbh.php_request("generic_select", parameters, values2);

			if (!result.startsWith("<br") /*result.length()>2*/) {
				result = dbh.parse_json_string_arr(result).get(0).getAsString();
				// Notification.show(result);
				UI.getCurrent().getPage().open(result, "_blank");

			} else {
				Notification.show("No transcript associated with this user");
			}

		});
		Button load = new Button("Re-upload Transcript", 
				event -> {
					DBHelper dbh1=new DBHelper();
					String[] params = {"student_num"};
					String[] values = {student_number.toString()};
					dbh1.php_request("sendStudentNum", params, values1);
					getUI().getPage().open("http://lamp.ms.wits.ac.za/~s1601745/uploadTranscript.html", "_blank");
					 					dbh1.php_request("update_transcript", params, values);		
					 					});
		
		//create button for user to click on to change password
		Button updatePassword = new Button("Change Password", 
				event1 -> {
					//create new panel view consisting of widgets below for user to change password
					
					
					p.setVisible(true);
					p.setHeight("260px");
					p.setWidthUndefined();
					
					details.addComponent(p);
					
					FormLayout fl = new FormLayout();
					fl.setMargin(true);
					
					//the tutor/lecturer fills in their student number for security purposes
					PasswordField current = new PasswordField();
					current.setCaption("Enter Current Password:");
					fl.addComponent(current);
					
					//the tutor/lecturer fills in their new password
					PasswordField new_password = new PasswordField();
					new_password.setCaption("Enter New Password:");
					fl.addComponent(new_password);
					
					//the tutor/lecturer confirms their new password
					PasswordField confirm_new = new PasswordField();
					confirm_new.setCaption("Confirm New Password:");
					fl.addComponent(confirm_new);
					
					//once the confirm button to hange password is clicked, do the following:
					Button confirmPass = new Button("Confirm Password", 
							event2 -> {
								
								//we use generic_select php to get the current password of user
								//so that we can confirm that the user inputs the correct password
								DBHelper dbh1=new DBHelper();
								String[] par = { "table", "target", "filter", "value" };
								String[] val = {"USER_INFORMATION","USER_PASSWORD","STUDENT_NUM", student_number};
								
								//code below formats current password, to get rid of brackets and commas
								String currPassword=dbh1.php_request("generic_select", par, val);
								JsonArray test=dbh.parse_json_string_arr(currPassword);
								String the_password=test.getAsJsonArray().get(0).getAsJsonArray().get(0).toString();
								the_password=the_password.substring(1, the_password.length()-1);
								
								//throw the following user-errors if the user does not fill their details correctly
								if(current.isEmpty()) {
									current.setComponentError(new UserError("Please fill in your current password"));
								}
								
								if(new_password.isEmpty()) {
									new_password.setComponentError(new UserError("Please fill in your new password"));
								}
								
								if(confirm_new.isEmpty()) {
									confirm_new.setComponentError(new UserError("Please confirm your new password"));
								}
								
								//we check if the current password entered is correct, and if the new password is validated by the user
							    if(the_password.equals(current.getValue().toString()) 
					    		&& new_password.getValue().toString().equals(confirm_new.getValue().toString())) {
							    
							    	//if the above condition is true, then we use php to update the database wit the new password
							    	
									String[] params = {"password","student_num"};
									
									
									String[] values = {confirm_new.getValue().toString(), student_number};
									dbh1.php_request("update_password", params, values);
									Notification.show("Password changed Successfully");
									p.setVisible(false);
									
							    }else {
							    	confirm_new.setComponentError(new UserError("Make sure all entries are correct"));
							    }
								
					});
					
					fl.addComponent(confirmPass);
					p.setContent(fl);
		});
		
		
	
		
		transcript_line.addComponent(load);
		transcript_line.addComponent(pdf_button);
		transcript_line.addComponent(updatePassword);
		
		//when a tutor profile is being viewed, we should display the button to view the tutor's transcript
		if(user.role.equals("0")) {
			pdf_button.setVisible(true);
		}
		//else remove the button, since no other role requires a transcript
		else {
			pdf_button.setVisible(false);
		}
		
		

		//if admin or super-admin views tutor profile, remove the upload transcript and password change functionality
		if(test.role.equals("2") || test.role.equals("4") && user.role.equals("0")) {
			text.setVisible(false);
			load.setVisible(false);
			updatePassword.setVisible(false);
		}
		
		//if admin or super-admin views lecturer profile, remove all transcript buttons and password change functionality
		else if(test.role.equals("2") || test.role.equals("4") && user.role.equals("1")) {
			text.setVisible(false);
			load.setVisible(false);
			updatePassword.setVisible(false);
		}
		
		//if lecturer views tutor profile remove transcripts and password change functionality
		if(test.role.equals("1") && user.role.equals("0")) {
			text.setVisible(false);
			load.setVisible(false);
			updatePassword.setVisible(false);
		}
		
		
		//when lecturer views own profile, remove transcripts and email button
		if(test.role.equals("1") && user.role.equals("1")) {
			email_button.setVisible(false);
			text.setVisible(false);
			load.setVisible(false);
		}
		
		
		
		
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
	public Panel make_courses_panel(JsonArray courses_obj) {
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
		course_combo_box.setCaption("Please Select The Courses You Would Like to Tutor(Max. 5)");
		course_combo_box.setItems(list);
		course_combo_box.setVisible(false);
		try {
			for (int x = 0; x < courses_obj.size(); x++) {
				try {
					String course_code = ((JsonArray) courses_obj.get(x)).get(0).getAsString();
					String course_conf = ((JsonArray) courses_obj.get(x)).get(1).getAsString();
					
					String course_name = get_course_name(course_code);
					course_combo_box.select(course_code);
					// \t is just a tab
					if (course_conf.equals("1")) {
						courses_inner.addComponent(new Label(course_code + "\t-\t" + course_name + "\n"));
					} else {
						courses_inner.addComponent(new Label(course_code + "\t-\t" + course_name + " (Pending confirmation) \n"));
					}

				} catch (UnsupportedOperationException e) {
					// TODO
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			courses_inner.addComponent(new Label("There's nothing here... \n"));
		}

		

		if (test.role.equals("0")) {
			Button change_courses = new Button("Change courses", event -> course_combo_box.setVisible(true));
			change_courses.addClickListener(event -> change_courses.setVisible(false));
			Button done = new Button("Done", event -> change_courses.setVisible(true));
			done.addClickListener(event -> done.setVisible(false));
			done.addClickListener(event -> course_combo_box.setVisible(false));
			done.addClickListener(event -> {
				DBHelper dbh = new DBHelper();
				String[] params = {"student_num","course","role","name"}; 
				for(String item : list)
				{
					String[] values = {user.get_student_num(),item,user.get_role(),user.get_name()};
					if(course_combo_box.getSelectedItems().contains(item))
					{
						dbh.php_request("add_course", params, values);
					}
					else
					{
						dbh.php_request("remove_course", params, values);
					}
					
				}
				this.removeAllComponents();
				this.enter(null);
				
			});
			done.setVisible(false);

			change_courses.addClickListener(event -> done.setVisible(true));

			courses_inner.addComponent(change_courses);
			courses_inner.addComponent(course_combo_box);
			courses_inner.addComponent(done);
		}

		panel.setCaption("Courses");
		panel.setContent(courses_inner);

		return panel;
	}

	public boolean handle_course_change(ArrayList<String> courses, String stud_num) {
		boolean result = true;
		for (String course : courses) {
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
	public boolean update_courses(String stud_num, String course, String confirmed) {
		boolean result = false;
		DBHelper dbh = new DBHelper();
		String value;
		String role = ((MyUI)getUI()).get_user_info().get_role();
		String name = ((MyUI)getUI()).get_user_info().get_name();

		if (confirmed.length() > 0) {
			String[] params = { "student_num", "course", "confirmed", "role", "name" };
			String[] values = { stud_num, course, confirmed, role, name };
			value = dbh.php_request("update_courses", params, values);
		} else {
			String[] params = { "student_num", "course" , "role", "name"};
			String[] values = { stud_num, course, role, name};
			value = dbh.php_request("update_courses", params, values);
		}

		result = Boolean.parseBoolean(value);

		return result;
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
					//Notification.show(data.get(0).getAsString()+data.get(1).getAsString());
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
	
	
	public String get_course_name(String course_code)
	{
		DBHelper dbh = new DBHelper();
		String[] params = { "table", "target", "filter", "value" };
		String[] values = {"COURSES", "COURSE_NAME", "COURSE_CODE",course_code};
		System.out.println("GET_COURSE_NAME "+course_code);
		String result = dbh.parse_json_string_arr(dbh.php_request("generic_select", params, values)).get(0).getAsString();;
		return result;
	}
}
