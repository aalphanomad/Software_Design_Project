package com.alphanomad.AN_Webapp;

public class HistoryItem{
	//Set the stuff here
	String Date;
	String Course;
	String Start_time;
	String  End_time;
	String Valid;
	String Venue;
	String Activity;
	
	
	public HistoryItem(String Date,String Course,String Start_time,String End_time,String Valid, String Venue,String Activity) {
		this.Date=Date;
		this.Course=Course;
		this.Start_time=Start_time;
		this.End_time=End_time;
		this.Valid=Valid;
		this.Venue=Venue;
		this.Activity=Activity;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String Date) {
		this.Date=Date;
	}
	public String getCourse() {
		return Course;
	}
	public void setCourse(String Course) {
		this.Course=Course;
	}
	public String getStart_Time() {
		return Start_time;
	}
	public void setStart_time(String Start_time) {
		this.Start_time=Start_time;
	}
	public String getEnd_time() {
		return End_time;
	}
	public void setEnd_time(String End_time) {
		this.End_time=End_time;
	}
	public String getValid() {
		return Valid;
	}
	public void setValid(String Valid) {
	this.Valid=Valid;
	}
	public String getVenue() {
		return Venue;
	}
	public void setVenue(String Venue) {
		this.Venue=Venue;
	}
	public String getActivity() {
		return Activity;
	}
	public void setActivity(String Activity) {
		this.Activity=Activity;
	}
}