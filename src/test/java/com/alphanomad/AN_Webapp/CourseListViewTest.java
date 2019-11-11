package com.alphanomad.AN_Webapp;

import static org.junit.Assert.*;

import org.junit.Test;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class CourseListViewTest {

	@Test
	public void test() {
		CourseListView coListView=new CourseListView();
		coListView.AddNewCourse();
		coListView.DeleteCourses();
		coListView.EditCourse();
		coListView.view_courses();
		coListView.get_all_courses();
		
		
	}

}
