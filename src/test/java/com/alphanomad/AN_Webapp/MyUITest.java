package com.alphanomad.AN_Webapp;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.Test;
import com.vaadin.*;
import com.vaadin.navigator.Navigator;

public class MyUITest {
	
	MyUI ui;
	@Before
	public void setup() throws Exception {
		//For MyUI.java
		ui=new MyUI();
		//ui.init(null);
		
	}
	

	@Test
	public void test() {
		ui.init(null);
		UserInfo user=new UserInfo("1899");
		ui.set_user_info(user);
		ui.get_user_info();
	}
	
	@After
	public void tearDown() throws Exception{
		//For MyUI.java
		//ui.close();
		
		//For MainView.java
		
	}

}
