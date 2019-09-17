package com.alphanomad.AN_Webapp;

public class Booking {
	
	public static String name, studnum, course, activity, date, venue, startTime, endTime;
	
	public Booking(String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8){
		
		name = str1;
        studnum = str2;
        course = str3;
        activity = str4;
        venue = str5;
        date = str6;
        startTime = str7;
        endTime = str8;
		
		String[] params = {"name","student_num","date", "course", "venue", "valid", "chkStartTime" , "chkEndTime", "activity"} ;
		String[] values= {name,studnum, date, course, venue, "0", startTime, endTime,  activity};
		DBHelper dbh = new DBHelper();
		dbh.php_request("booking", params, values);
		
	}

}
