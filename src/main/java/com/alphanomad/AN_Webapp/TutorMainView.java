package com.alphanomad.AN_Webapp;

import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.*;

public class TutorMainView extends VerticalLayout implements View
{

	public TutorMainView()
	{

	}

	@Override
	public void enter(ViewChangeEvent vc_event)
	{
		// THIS PREVENTS USERS FROM PRESSING BACK TO LOGIN WITHOUT A PASSWORD
		if (! ((MyUI)getUI()).logged_in)
		{
			getUI().getNavigator().navigateTo("login");
		} 
		
		removeAllComponents();
		DBHelper dbh = new DBHelper();

		// DEFINE COMPONENTS HERE
		
		//setting title for navigation bar and theme
		Label title = new Label("Menu");
        title.addStyleName(ValoTheme.MENU_TITLE);

        Button profile_button = new Button("Profile", event -> getUI().getNavigator().navigateTo("profile"));
        //profile_button.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);
        
        String studentNum = ((MyUI) getUI()).get_user_info().get_student_num();
        
        
		ArrayList<String> month_strs = new ArrayList<String>();
		month_strs.add("Jan");
		month_strs.add("Feb");
		month_strs.add("Mar");
		month_strs.add("Apr");
		month_strs.add("May");
		month_strs.add("Jun");
		month_strs.add("Jul");
		month_strs.add("Aug");
		month_strs.add("Sep");
		month_strs.add("Oct");
		month_strs.add("Nov");
		month_strs.add("Dec");
		ComboBox<String> months = new ComboBox<String>();
		months.setItems(month_strs);
		months.setDescription("Choose month to generate claim form for. Leave empty to get full claim history");
		//months.setCaption("Choose month to generate claim form for. Leave Blank to generate full record");
		months.setEmptySelectionCaption("Choose month...");
        
   
        
        Button pdf_button = new Button("Generate Claims Form", event -> 
		{
			if(months.getSelectedItem() != null)
			{
				String[] parameters = { "table", "target", "student_num", "month" };
				String[] valuess = { "BOOKINGS", "*", studentNum, months.getSelectedItem().get()};
				UI.getCurrent().getPage().open(dbh.php_request("select_booking", parameters, valuess), "_blank");
			}
			else
			{
				String[] parameters = { "table", "target", "student_num"};
				String[] valuess = { "BOOKINGS", "*", studentNum};
				UI.getCurrent().getPage().open(dbh.php_request("select_booking", parameters, valuess), "_blank");
			}
		});
        
        
		Button Login = new Button("Logout", event -> 
		 {
			 getUI().getNavigator().navigateTo("login");
			 ((MyUI)getUI()).logged_in = false;
			
		});
		// Button Register=new
		// Button("Register",event->getUI().getNavigator().navigateTo("register"));

		Button History = new Button("History", event -> getUI().getNavigator().navigateTo("history"));

		Button claimForm_button = new Button("New Claim", event -> getUI().getNavigator().navigateTo("claimform"));
		
		//add the following buttons to the navigation bar
        CssLayout menu = new CssLayout(title, claimForm_button, profile_button, History, Login);
        menu.addStyleName(ValoTheme.MENU_ROOT);	//apply certain theme to bar

        //certain css layout to apply to navigation bar
        CssLayout viewContainer = new CssLayout();

        //adding the navigation bar to the page
        HorizontalLayout mainLayout = new HorizontalLayout(menu, viewContainer);
        mainLayout.setSizeFull();
        addComponent(mainLayout);

		

		// ADD COMPONENTS TO UI HERE

		HorizontalLayout pdf_row = new HorizontalLayout();
		pdf_row.addComponent(pdf_button);
		pdf_row.addComponent(months);
		addComponent(pdf_row);
		// addComponent(Register);
		
	}

}
