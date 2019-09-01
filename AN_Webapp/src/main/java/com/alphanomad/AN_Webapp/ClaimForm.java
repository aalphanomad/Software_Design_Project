package com.alphanomad.AN_Webapp;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;


public class ClaimForm extends VerticalLayout implements View {
	String course;
    String venue;
    String activity;
    

    @SuppressWarnings("unchecked")
	public ClaimForm () {
    	
         
    }
    
    @Override
    public void enter(ViewChangeEvent event)
    {
//navigator=new Navigator(this,this);
    	removeAllComponents();
    	
    	UserInfo tutor_info = ((MyUI) getUI()).get_user_info();
    	Label test=new Label("<p style = \"font-family:georgia,garamond,serif;font-size:30px;\">\r\n" + 
	  	  		"       <b><u>Claim Form</u></b> " + 
	  	  		"      </p>" ,ContentMode.HTML);
	  	 addComponent(test);
        
        ArrayList<String> coursesArray = new ArrayList();
        coursesArray.add("COMS3003 (Formal Languages and Automata)");
        coursesArray.add("COMS3005 (Advanced Analysis of Algorithms)");
        coursesArray.add("COMS3009 (Software Design)");
        coursesArray.add("COMS3010 (Operating Systems and System Programming)");
        coursesArray.add("COMS3007 (Machine Learning)");
        coursesArray.add("COMS3006 (Computer Graphics and Visualization)");
        coursesArray.add("COMS3008 (Parallel Computing)");
        coursesArray.add("COMS3011 (Software Design Project)");
        
        ArrayList<String> activityArray = new ArrayList();
        activityArray.add("Tutoring");
        activityArray.add("Invigilating");
        activityArray.add("Marking");
        activityArray.add("Other");
        
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
        
        DateTimeField start = new DateTimeField();
        start.setCaption("Select start of duration");
        //start.setValue(LocalDateTime.now());
        addComponent(start);
        
        DateTimeField end = new DateTimeField();
        end.setCaption("Select end of duration");
        //end.setValue(LocalDateTime.now());
        addComponent(end);
        
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
        see.addClickListener(event -> Notification.show(date, Type.TRAY_NOTIFICATION));
        addComponent(see);*/
		
        
       

        Button confirm = new Button("Confirm");
        addComponent(confirm);
      
            confirm.addClickListener(e->{new ConfirmClaimForm(tutor_info.name, tutor_info.student_num, 
            		EditString.editCourse((combobox.getValue().toString())), combobox2.getValue().toString(), 
            		textfield.getValue().toString(), date, EditString.editTime(start.getValue().toString()), 
            		EditString.editTime(end.getValue().toString()));
            getUI().getNavigator().navigateTo("confirm");
            });
    }

}