package com.alphanomad.AN_Webapp;

public class EditString
{
	//the sole purpose of this class is to simplify edit dirty/ugly strings for some classes outside
	public static String editTime(String time)
	{
		time = time.substring(11, time.length());
		return time;
	}

	public static String editCourse(String course)
	{
		course = course.substring(0, 9);
		return course;
	}

}
