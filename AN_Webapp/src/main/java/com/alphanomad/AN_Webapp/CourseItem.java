package com.alphanomad.AN_Webapp;

public class CourseItem
{
	
	String course_code;
	String course_name;
	
	public CourseItem(String course_code, String course_name)
	{
		this.course_code = course_code;
		this.course_name = course_name;
	}
	
	public String getCourse_code()
	{
		return course_code;
	}
	public void setCourse_code(String course_code)
	{
		this.course_code = course_code;
	}
	public String getCourse_name()
	{
		return course_name;
	}
	public void setCourse_name(String course_name)
	{
		this.course_name = course_name;
	}
}
