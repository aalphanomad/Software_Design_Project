package com.alphanomad.AN_Webapp;

import static org.junit.Assert.*;

import org.junit.Test;

public class EditStringTest {

	@Test
	public void testEditTime() {
		String time="10:1000000000000000000";
		String test=time.substring(11, time.length());
		EditString editString=new EditString();
		editString.editTime(time);
		//assertEquals(test,editString.editTime(time));
	}



}
