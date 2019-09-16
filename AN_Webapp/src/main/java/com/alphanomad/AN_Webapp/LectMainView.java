package com.alphanomad.AN_Webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.gson.JsonObject;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.ImageRenderer;


public class LectMainView extends VerticalLayout implements View{
	JsonObject retrieve;
	int SelIndex;
	ArrayList<TutorItem>Tutor=new ArrayList<TutorItem>();
	
    public ArrayList<String> Filter_Info(String Dirty_Info){
    	ArrayList<String>final_Array=new ArrayList<String>();

    	String[] test=Dirty_Info.split(":");
    	if(test[1].length()==1 || test[1].substring(0, 3).equals("[]}")) {
    		final_Array.add("-1");
    		return final_Array;
    	}
    	else {
    	String[] filtered_Array1=Dirty_Info.split(":");
    	filtered_Array1[1]=filtered_Array1[1].substring(1, filtered_Array1[1].length());

    	String[] filtered_Array2=filtered_Array1[1].split(",");
    	for(int i=0;i<filtered_Array2.length;i++) {
    		if(!filtered_Array2[i].equals("null")) {
    			final_Array.add(filtered_Array2[i].substring(1,filtered_Array2[i].length()-1));
    		}
    	}	
    	return final_Array;
    	}
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
	 
	 

     String ans2=dbh.php_request("get_students", new String[] {"student_num"}, new String[] {lect_info.student_num});
     ans2=ans2.substring(1, ans2.length()-1);
  // Notification.show(ans2);
     String[]AllInfo=ans2.split("\\],\"");
     ArrayList<String>TheCourses=Filter_Info(AllInfo[0]);
     ArrayList<String>Course1=Filter_Info(AllInfo[1]);
     ArrayList<String>Course2=Filter_Info(AllInfo[2]);
     ArrayList<String>Course3=Filter_Info(AllInfo[3]);
     ArrayList<String>Course4=Filter_Info(AllInfo[4]);
     ArrayList<String>Course5=Filter_Info(AllInfo[5]);
     
     ArrayList<String>StudCourse1=Filter_Info(AllInfo[6]);
     ArrayList<String>StudCourse2=Filter_Info(AllInfo[7]);
     ArrayList<String>StudCourse3=Filter_Info(AllInfo[8]);
     ArrayList<String>StudCourse4=Filter_Info(AllInfo[9]);
     ArrayList<String>StudCourse5=Filter_Info(AllInfo[10]);

    	


	        
	        ComboBox<String> combobox = new ComboBox<String>("Course Selected");
	        combobox.setPlaceholder("Please Select A Course");
	        combobox.setWidth("40%");
	        combobox.setItems(myCourses);
	        addComponent(combobox);
	        combobox.setValue(myCourses.get(0));

	        
	        Grid<TutorItem> grid = new Grid<>(TutorItem.class);
	        grid.getColumn("image").setRenderer(new ImageRenderer());
	        grid.setColumnOrder("image","name","student_num");
	        grid.setWidth("100%");
	      // grid.setWidthUndefined();
	        /*
	   grid.addComponentColumn(probe->{
		   Image image=new Image("",new ExternalResource("https://sophosnews.files.wordpress.com/2014/04/anonymous-250.jpg?w=250"));
		   image.setWidth(100, Unit.PIXELS);
		   image.setHeight(100, Unit.PIXELS);
		   return image;

		   
	   }).setCaption("Profile Picture").setId("Profile Picture");
	   */
	        grid.setRowHeight(100);
	        grid.setHeaderRowHeight(30);
	        
	        grid.addColumn(unused -> "More Info", new ButtonRenderer<Object>(
	    			event ->{
	    				//UserInfo test = new UserInfo("1"); 
	    				//System.out.println("LECTVIEW:  " + test.student_num);
	    				
	    			 new ProfileView((MyUI) getUI(),(((TutorItem) event.getItem()).getStudent_num())); 
	    			getUI().getNavigator().navigateTo("profile");
	    			}));	
	        
	        for(int i=0;i<Course1.size();i++) {
        		Tutor.add(new TutorItem(Course1.get(i),StudCourse1.get(i)));
        	}
	        grid.setItems(Tutor);
	        addComponent(grid);

	        combobox.addValueChangeListener(event->{combobox.setValue(combobox.getValue());
	      

	    Tutor.clear();
	        SelIndex=myCourses.indexOf(combobox.getValue());
	        switch(SelIndex) {
	        case 0:
	        	for(int i=0;i<Course1.size();i++) {
	        		Tutor.add(new TutorItem(Course1.get(i),StudCourse1.get(i)));
	        	}
	        	break;
	        case 1:
	        	for(int i=0;i<Course2.size();i++) {
	        		Tutor.add(new TutorItem(Course2.get(i),StudCourse2.get(i)));
	        	}
	        	break;
	        case 2:
	        	for(int i=0;i<Course3.size();i++) {
	        		Tutor.add(new TutorItem(Course3.get(i),StudCourse3.get(i)));
	        	}
	        	break;
	        case 3:
	        	for(int i=0;i<Course4.size();i++) {
	        		Tutor.add(new TutorItem(Course4.get(i),StudCourse4.get(i)));
	        	}
	        	break;
	        case 4:
	        	for(int i=0;i<Course5.size();i++) {
	        		Tutor.add(new TutorItem(Course5.get(i),StudCourse5.get(i)));
	        	}
	        	break;
	        }
	        
	       grid.setItems(Tutor);



	        
});
    

	    

 
//Sort out empty arrays!!!!!!!!!!!!!!!!!!!!!1
	        
	      //  ArrayList<String> CCourse5=Filter_Info(Course5);
	      //  Notification.show(CCourse5.toString());
	        
	        
	        
	        
	        

	        

//Notification.show(Final_Courses.toString());


	        
	}
	    
		
	
}

 

