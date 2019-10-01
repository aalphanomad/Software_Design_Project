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
	
	public ChooseRole()
	{
	
	}
	
	@Override
	public void enter(ViewChangeEvent event){
		
		UserInfo info = ((MyUI) getUI()).get_user_info();
		if(info.student_num.equals("4")) {
			Lecturer_Admin();
		}

	}
	
	public void Lecturer_Admin() {
		
		removeAllComponents();
		setSizeFull();
		addStyleName("image-backgound");
		
		Panel panel = new Panel();
		panel.setHeight("250px");
		panel.setWidthUndefined();
		addComponent(panel);
		setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

		FormLayout content = new FormLayout();
		content.addStyleName("Template");
		content.setMargin(true);
		Label test = new Label("<p style = \"font-family:georgia,garamond,serif;font-size:30px;\">\r\n"
				+ "       <b><u>Login in as: </u></b> " + "      </p>", ContentMode.HTML);
		content.addComponent(test);



		HorizontalLayout buttons = new HorizontalLayout();

		Button button1 = new Button("Lecturer", e -> getUI().getNavigator().navigateTo("lectmain"));
		button1.setClickShortcut(KeyCode.ENTER);
		Button button2 = new Button("Admin", e -> getUI().getNavigator().navigateTo("adminmain"));
		buttons.addComponent(button1);
		buttons.addComponent(button2);
		content.addComponent(buttons);
		buttons.setComponentAlignment(button1, Alignment.BOTTOM_LEFT);
		buttons.setComponentAlignment(button2, Alignment.BOTTOM_RIGHT);
		content.setComponentAlignment(test, Alignment.MIDDLE_CENTER);
		content.setComponentAlignment(buttons, Alignment.BOTTOM_LEFT);

		panel.setContent(content);
	}

}
