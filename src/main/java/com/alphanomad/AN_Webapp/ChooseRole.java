package com.alphanomad.AN_Webapp;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ChooseRole extends VerticalLayout implements View{
	public TextField Username;
	public PasswordField Password;
	
	//this class is created for the sole purpose of catering to lecturer/admin roles. 
	//once they login, they will be directed to this page where they can choose whether to login as lecturer or admin.
	
	public ChooseRole()
	{
	
	}
	
	@Override
	public void enter(ViewChangeEvent event){
		
		//get the details of the role of the login user
		UserInfo info = ((MyUI) getUI()).get_user_info();
		if(info.role.equals("3")) {	
			Lecturer_Admin();
		}

	}
	
	public void Lecturer_Admin() {
		
		
		removeAllComponents();
		setSizeFull();
		addStyleName("image-backgound");
		
		//adding a panel for better UI look
		Panel panel = new Panel();
		panel.setHeight("250px");
		panel.setWidthUndefined();
		addComponent(panel);
		setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

		//
		FormLayout content = new FormLayout();
		content.addStyleName("Template");
		content.setMargin(true);
		Label test = new Label("<p style = \"font-family:georgia,garamond,serif;font-size:30px;\">\r\n"
				+ "       <b><u>Login in as: </u></b> " + "      </p>", ContentMode.HTML);
		content.addComponent(test);



		//creating horizontal layout for buttons to appear next to each other. 
		HorizontalLayout buttons = new HorizontalLayout();

		//first button to enter the page for a lecturer
		Button button1 = new Button("Lecturer", e -> getUI().getNavigator().navigateTo("lectmain"));
		button1.setClickShortcut(KeyCode.ENTER);
		 
		//second button to enter the page for an admin
		Button button2 = new Button("Admin", e -> getUI().getNavigator().navigateTo("adminmain"));
		
		//add buttons to horizontal layout
		buttons.addComponent(button1);
		buttons.addComponent(button2);
		content.addComponent(buttons);
		
		//allign the layout on the page
		buttons.setComponentAlignment(button1, Alignment.BOTTOM_LEFT);
		buttons.setComponentAlignment(button2, Alignment.BOTTOM_RIGHT);
		content.setComponentAlignment(test, Alignment.MIDDLE_CENTER);
		content.setComponentAlignment(buttons, Alignment.BOTTOM_LEFT);

		panel.setContent(content);
	}

}
