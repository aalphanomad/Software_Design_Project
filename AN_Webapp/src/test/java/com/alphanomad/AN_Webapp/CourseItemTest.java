package com.alphanomad.AN_Webapp;

import static org.junit.Assert.*;

import org.junit.Test;

public class CourseItemTest {

	@Test
	public void test() {
		CourseItem courseItem=new CourseItem("COMS3010", "Operating Systems");
		courseItem.getCourse_code();
		courseItem.getCourse_name();
	}

}
