package com.alphanomad.AN_Webapp;

import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.JsonObject;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.*;


public class LectMainView extends VerticalLayout implements View{
	JsonObject retrieve;
	
    public ArrayList<String> Filter_Info(String Dirty_Info){
    	String[] filtered_Array1=Dirty_Info.split(":");
    	filtered_Array1[1]=filtered_Array1[1].substring(1, filtered_Array1[1].length());

    	String[] filtered_Array2=filtered_Array1[1].split(",");
    	ArrayList<String>final_Array=new ArrayList<String>();
    	for(int i=0;i<filtered_Array2.length;i++) {
    		if(!filtered_Array2[i].equals("null")) {
    			final_Array.add(filtered_Array2[i].substring(1,filtered_Array2[i].length()-1));
    		}
    	}	
    	return final_Array;
    }
	public LectMainView() {


    }
	
	@Override
	public void enter(ViewChangeEvent vc_event)
	{
	
		UserInfo lect_info=((MyUI) getUI()).get_user_info();
		removeAllComponents();
		DBHelper dbh = new DBHelper();
		 String[]params1= {"name","student_num"};
		 String[]values1= {lect_info.name,lect_info.student_num};
		 String ans1=dbh.php_request("get_courses", params1, values1);
		 retrieve=dbh.parse_json_string(ans1);
			ans1=retrieve.get("result").getAsJsonArray().toString();		

				 	
		 
	 ArrayList<String>myCourses=ClaimForm.GetCourses(ans1);
	 for(int i=0;i<myCourses.size();i++) {
		 myCourses.set(i, myCourses.get(i)+" "+ClaimForm.Course_corr(myCourses.get(i)));
	 }
	       
	        
	        ComboBox<String> combobox = new ComboBox<String>("Course Selected");
	        combobox.setPlaceholder("Please Select A Course");
	        combobox.setWidth("40%");
	        combobox.setItems(myCourses);
	        addComponent(combobox);
	       
	        String ans2=dbh.php_request("get_students", new String[] {"student_num"}, new String[] {lect_info.student_num});
	        ans2=ans2.substring(1, ans2.length()-1);
	     // Notification.show(ans2);
	        String[]AllInfo=ans2.split("\\],\"");
	        String TheCourses=AllInfo[0];
	        String Course1=AllInfo[1];
	        String Course2=AllInfo[2];
	        String Course3=AllInfo[3];
	        String Course4=AllInfo[4];
	        String Course5=AllInfo[5];
	        String StudCourse1=AllInfo[6];
	        String StudCourse2=AllInfo[7];
	        String StudCourse3=AllInfo[8];
	        String StudCourse4=AllInfo[9];
	        String StudCourse5=AllInfo[10];

//Sort out empty arrays!!!!!!!!!!!!!!!!!!!!!1
	        
	      //  ArrayList<String> CCourse5=Filter_Info(Course5);
	      //  Notification.show(CCourse5.toString());
	        
	        
	        
	        
	        

	        

//Notification.show(Final_Courses.toString());


	        
	}
	    
		
	
}


