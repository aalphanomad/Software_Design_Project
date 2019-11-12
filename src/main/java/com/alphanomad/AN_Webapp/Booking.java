package com.alphanomad.AN_Webapp;

import com.google.gson.JsonObject;
import com.vaadin.ui.Notification;

public class Booking
{
	//This is the actual function that is called to make the entry in the databse when a tutor generates a new claim
	//takes in name, studnum, course, activity, date, venue, startTime, endTime
	public static String name, studnum, course, activity, date, venue, startTime, endTime, ans;
	JsonObject filtered;

	public Booking(String str1, String str2, String str3, String str4, String str5, String str6, String str7,
			String str8)
	{

		name = str1;
		studnum = str2;
		course = str3;
		activity = str4;
		venue = str5;
		date = str6;
		startTime = str7;
		endTime = str8;

		String[] params = { "name", "student_num", "date", "course", "venue", "valid", "chkStartTime", "chkEndTime",
				"activity" };
		String[] values = { name, studnum, date, course, venue, "0", startTime, endTime, activity };
		DBHelper dbh = new DBHelper();
		ans = dbh.php_request("booking", params, values);
	
		filtered = dbh.parse_json_string(ans);
		ans = filtered.get("result").getAsString().substring(0, filtered.get("result").getAsString().length());
	}

}
