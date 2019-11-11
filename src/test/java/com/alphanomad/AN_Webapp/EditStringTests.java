package com.alphanomad.AN_Webapp;

import static org.junit.Assert.*;

import org.junit.Test;

public class EditStringTests {

	@Test
	public void test() {
		String time="10:1000000000000000000";
		String test=time.substring(11, time.length());
		EditString editString=new EditString();
		editString.editTime(time);
		//assertEquals(test,editString.editTime(time));
		
		String course="SoftwareDesign2";
		String test2=course.substring(0, 8);
		editString.editCourse(course);
		//assertEquals(test,editString.editCourse(course));
	}

}
