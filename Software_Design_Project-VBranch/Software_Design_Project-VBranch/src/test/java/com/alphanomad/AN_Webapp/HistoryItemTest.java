package com.alphanomad.AN_Webapp;

import static org.junit.Assert.*;

import org.junit.Test;

public class HistoryItemTest {

	@Test
	public void testHistoryItem() {
		String Date="12-09-2019";
		String Course="Software Design";
		String Start_time="08:00";
		String  End_time="09:00";
		String Valid="true";
		String Venue="MSL";
		String Activity="LoginView";
		
		HistoryItem historyItem=new HistoryItem(Date, Course, Start_time, End_time, Valid, Venue, Activity);
		assertEquals(Date, historyItem.getDate());
		assertEquals(Course, historyItem.getCourse());
		assertEquals(Valid, historyItem.getValid());
		assertEquals(Start_time, historyItem.getStart_Time());
		assertEquals(End_time, historyItem.getEnd_time());
		assertEquals(Activity, historyItem.getActivity());
		assertEquals(Venue,historyItem.getVenue());
		historyItem.setDate(Date);
		historyItem.setCourse(Course);
		historyItem.setStart_time(Start_time);
		historyItem.setEnd_time(End_time);
		historyItem.setValid(Valid);
		historyItem.setVenue(Venue);
		historyItem.setActivity(Activity);
		
	}

}
