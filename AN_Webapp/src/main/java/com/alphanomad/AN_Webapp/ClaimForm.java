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


public class ClaimForm extends VerticalLayout implements View {
	String course;
    String venue;
    String activity;
    
    String[] AllInfo;
    static JsonObject filtered;
    static DBHelper dbh;
    
    Booking b;
    ConfirmClaimForm c;
    
    
    
    public static ArrayList<String>  GetCourses(String FromDB){
    	
		//Notification.show(ans);
		ArrayList<String> new_courses=new ArrayList<String>();
		
		String[]courses1=FromDB.split(",");
		for(int i=0;i<courses1.length;i++) {
			String[]courses2=courses1[i].split(":");
			if(i==courses1.length-1) {
				if(!courses2[1].substring(0, courses2[1].length()-2).equals("null")) {
					new_courses.add(courses2[1].substring(1, courses2[1].length()-3));

				}
			}else {
			if(!courses2[1].equals("null"))
			new_courses.add(courses2[1].substring(1, courses2[1].length()-1));
			}
		}
		return new_courses;
	}
    
    
    public static String Course_corr(String course_code){
        String ans=null;
        switch(course_code){
            case "COMS1015":
                ans= "(Basic Computer Organisation)";
                break;
            case "COMS1018":
                ans= "(Introduction to Algorithms and Programming)";
                break;
            case "COMS1017":
                ans= "(Introduction to Data Structures and Algorithms)";
                break;
            case "COMS1016":
                ans= "(Discrete Computational Structures)";
                break;
            case "COMS2002":
                ans= "(Database Fundamentals)";
                break;
            case "COMS2013":
                ans= "(Mobile Computing)";
                break;
            case "COMS2014":
                ans= "(Computer Networks)";
                break;
            case "COMS2015":
                ans= "(Analysis of Algorithms)";
                break;
            case "COMS3002":
                ans= "(Software Engineering)";
                break;
            case "COMS3003":
                ans= "(Formal Languages and Automata)";
                break;
            case "COMS3005":
                ans= "(Advanced Analysis of Algorithms)";
                break;
            case "COMS3009":
                ans= "(Software Design)";
                break;
            case "COMS3010":
                ans= "(Operating Systems and System Programming)";
                break;
            case "COMS3007":
                ans= "(Machine Learning)";
                break;
            case "COMS3006":
                ans= "(Computer Graphics and Visualisation)";
                break;
            case "COMS3008":
                ans= "(Parallel Computing)";
                break;
            case "COMS3011":
                ans= "(Software Design Project)";
                break;


        }
        return ans;

    }
    
    public String[] getAllInfo() {
    	return AllInfo.clone();
    }

    @SuppressWarnings("unchecked")
	public ClaimForm () {
    	
         
    }
    
    @Override
    public void enter(ViewChangeEvent event)
    {
		removeAllComponents();
	 	dbh=new DBHelper();
		UserInfo tutor_info = ((MyUI) getUI()).get_user_info();
		
		Label test=new Label("<p style = \"font-family:georgia,garamond,serif;font-size:30px;\">\r\n" + 
		"       <b><u>Claim Form</u></b> " + 
		"      </p>" ,ContentMode.HTML);
		 addComponent(test);
		 
		String[] params= {"name","student_num"};
		String[] values= {tutor_info.name,tutor_info.student_num};
		
		String ans=dbh.php_request("get_courses", params, values);
		filtered=dbh.parse_json_string(ans);
		ans=filtered.get("result").getAsJsonArray().toString();		
			
		ArrayList<String>new_courses=GetCourses(ans);
   
	    ArrayList<String> coursesArray = new ArrayList();
	    for(int i=0;i<new_courses.size();i++) {
	    	coursesArray.add(new_courses.get(i)+" "+Course_corr(new_courses.get(i)));
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
        
        
        
        ComboBox<String> combobox = new ComboBox<String>("Course");
        combobox.setPlaceholder("Please fill in");
        combobox.setWidth("100%");
        combobox.setItems(coursesArray);
        addComponent(combobox);
        
        TextField textfield = new TextField();
        textfield.setPlaceholder("Please fill in");
        textfield.setCaption("Venue");
        addComponent(textfield);
        
        ComboBox<String> combobox2 = new ComboBox<String>("Type of Activity");
        combobox2.setPlaceholder("Please fill in");
        combobox2.setItems(activityArray);
        addComponent(combobox2);
        
        ComboBox<String> startHour = new ComboBox<String>("Hour(s)");
        startHour.setPlaceholder("Please fill in");
        startHour.setItems(hour);
        
        ComboBox<String> startMinute = new ComboBox<String>("Minutes");
        startMinute.setPlaceholder("Please fill in");
        startMinute.setItems(minute);
        
        ComboBox<String> endHour = new ComboBox<String>("Hour(s)");
        endHour.setPlaceholder("Please fill in");
        endHour.setItems(hour);
        
        ComboBox<String> endMinute = new ComboBox<String>("Minutes");
        endMinute.setPlaceholder("Please fill in");
        endMinute.setItems(minute);
        
        
		HorizontalLayout time1=new HorizontalLayout();
		
		Label st=new Label("<p style = \"font-family:georgia,garamond,serif;font-size:15px;\">\r\n" + 
				"       <u>Start-Time</u> " + 
				"      </p>" ,ContentMode.HTML);
		
		time1.addComponent(startHour);
		time1.addComponent(startMinute);
		time1.addComponent(st);
		addComponent(time1);
		
		HorizontalLayout time2=new HorizontalLayout();
		
		Label ed=new Label("<p style = \"font-family:georgia,garamond,serif;font-size:15px;\">\r\n" + 
				"       <u>End-Time</u> " + 
				"      </p>" ,ContentMode.HTML);
		  	
	  	time2.addComponent(endHour);
	  	time2.addComponent(endMinute);
	  	time2.addComponent(ed);
	  	addComponent(time2);
	  	
        /*DateTimeField start = new DateTimeField();
        start.setCaption("Select start of duration");
        //start.setValue(LocalDateTime.now());
        addComponent(start);
        
        DateTimeField end = new DateTimeField();
        end.setCaption("Select end of duration");
        //end.setValue(LocalDateTime.now());
        addComponent(end);*/
	  	
        
        if(!combobox.isEmpty() && !textfield.isEmpty() && !combobox2.isEmpty()) {
	        course = combobox.getValue().toString();
	        venue = textfield.getValue().toString();
	        activity = combobox2.getValue().toString();
        }
        
        
			
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date today = new Date();

		String day = today.toString().substring(8,10);
		String month = today.toString().substring(4,7);
		String year = today.toString().substring(24,today.toString().length());
		
		String date = day + " " + month + " " + year;
		
		/*Button see = new Button("SEE");
		//see.addClickListener(event -> dbh.php_request("booking", params, values));
        see.addClickListener(e -> Notification.show(startHour.getValue().toString(), Type.TRAY_NOTIFICATION));
        addComponent(see);*/
		
        
        //navigator.addView(CONFIRMCLAIMFORM, new ClaimForm());
        
        Button confirm = new Button("Confirm");
        addComponent(confirm);
        confirm.addClickListener(e -> {
        			
            	b = new Booking(tutor_info.name, 
        			tutor_info.student_num, 
            		EditString.editCourse((combobox.getValue().toString())), 
            		combobox2.getValue().toString(), 
            		textfield.getValue().toString(), 
            		date, 
            		startHour.getValue().toString() + ":" + startMinute.getValue().toString(), 
            		endHour.getValue().toString() + ":" + endMinute.getValue().toString());
    
            	removeAllComponents();
            	
		        addComponent(c = new ConfirmClaimForm(tutor_info.name, 
		        		tutor_info.student_num, 
		        		EditString.editCourse((combobox.getValue().toString())), 
		        		combobox2.getValue().toString(), 
		        		textfield.getValue().toString(), 
		        		date, 
		        		startHour.getValue().toString() + ":" + startMinute.getValue().toString(), 
		        		endHour.getValue().toString() + ":" + endMinute.getValue().toString()));
          
				//navigator.addView(CONFIRMCLAIMFORM, c);
				//addComponent(c);
				//getUI().getNavigator().navigateTo("confirm");
	        			
			});
    }

}
