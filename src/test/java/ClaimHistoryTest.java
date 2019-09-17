//package com.alphanomad.AN_Webapp;
import com.alphanomad.AN_Webapp.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class ClaimHistoryTest {

	@Test
	public void testDisplay() {
		ClaimHistory claimHistory=new ClaimHistory();
		TextField Name;
		Name=new TextField();
		Name.setIcon(VaadinIcons.USER);
		Name.setCaption("Name"); 
		Name.setPlaceholder("Name");
		
		PasswordField Password;
		 Password=new PasswordField();
	  	  Password.setCaption("Password");
	  	  Password.setIcon(VaadinIcons.PASSWORD);
	  	  Password.setPlaceholder("Password");
	  	  
	  	  claimHistory.Password=Password;
	  	  claimHistory.Username=Name;
		
		String info="{\"dates\":[{\"DATE\":\"23 Aug 2019\"},{\"DATE\":\"24 Aug 2019\"},{\"DATE\":\"25 Aug 2019\"}],\"courses\":[{\"COURSE\":\"COMS3010\"},{\"COURSE\":\"COMS3010\"},{\"COURSE\":\"COMS3010\"}],\"start_time\":[{\"START_TIME\":\"14:30:20\"},{\"START_TIME\":\"14:30:20\"},{\"START_TIME\":\"14:30:20\"}],\"end_time\":[{\"END_TIME\":\"14:35:00\"},{\"END_TIME\":\"14:35:00\"},{\"END_TIME\":\"14:35:00\"}],\"valid\":[{\"VALID\":\"0\"},{\"VALID\":\"0\"},{\"VALID\":\"0\"}],\"venue\":[{\"VENUE\":\"MMM\"},{\"VENUE\":\"MMM\"},{\"VENUE\":\"MMM\"}],\"activity\":[{\"ACTIVITY\":\"\"},{\"ACTIVITY\":\"Tutoring\"},{\"ACTIVITY\":\"Tutoring\"}]}";
		int size=3;
		
		//String[][] test=claimHistory.Display(info, size);
	}


}
