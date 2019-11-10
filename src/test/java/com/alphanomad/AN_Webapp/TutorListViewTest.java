package com.alphanomad.AN_Webapp;

import static org.junit.Assert.*;

import org.junit.Test;

public class TutorListViewTest {

	@Test
	public void test() {
		//fail("Not yet implemented");
		TutorListView tutorListView=new TutorListView();
		tutorListView.view_users();
		
		tutorListView.get_selected_users("4"); 	
	}

}
