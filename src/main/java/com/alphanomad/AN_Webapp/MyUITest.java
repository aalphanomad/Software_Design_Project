package com.alphanomad.AN_Webapp;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.*;
import com.alphanomad.AN_Webapp.*;
import com.vaadin.*;
import com.vaadin.navigator.Navigator;

public class MyUITest {
	
	private static final String URL="http://localhost";
	private static final String PORT="8080";
	MyUI ui;
	MainView mainView;
	@Before
	public void setup() throws Exception {
		//For MyUI.java
		ui=new MyUI();
		ui.init(null);
		
		//For MainView.java
		mainView=new MainView();
	}
	
	@After
	public void tearDown() throws Exception{
		//For MyUI.java
		ui.close();
		
		//For MainView.java
		
	}
	
	@Test
	public void test() {
		//For MyUI.java
		String v="main";
		String vv="profile";
		String vvv="login";
		String vvvv="register";
		assertEquals(v, ui.MAINVIEW);
		assertEquals(vv, ui.PROFILEVIEW);
		assertEquals(vvv, ui.LOGINVIEW);
		assertEquals(vvvv, ui.REGVIEW);
		
		Navigator navigator=new Navigator(ui, ui);
		
		assertNotNull(navigator);
		assertNotNull(ui);
		
		//For MainView.java
		
	}

}
