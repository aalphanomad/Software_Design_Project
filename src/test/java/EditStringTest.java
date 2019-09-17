//package com.alphanomad.AN_Webapp;
import com.alphanomad.AN_Webapp.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class EditStringTest {

	@Test
	public void testEditTime() {
		String time="10:1000000000000000000";
		String test=time.substring(11, time.length());
		EditString editString=new EditString();
		assertEquals(test,editString.editTime(time));
	}

	@Test
	public void testEditCourse() {
		String course="Software Design";
		String test=course.substring(0, 9);
		EditString editString=new EditString();
		assertEquals(test,editString.editCourse(course));
	}

}
