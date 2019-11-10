package com.alphanomad.AN_Webapp;

import static org.junit.Assert.*;

import org.junit.Test;

public class CourseAllocationViewTest {

	@Test
	public void test() {
		CourseAllocationView courseAllocationView=new CourseAllocationView();
		courseAllocationView.get_all_course_allocs();
		courseAllocationView.get_confirmed_course_allocs();
		courseAllocationView.get_unconfirmed_course_allocs();
		courseAllocationView.get_declined_course_allocs();
	}

}
