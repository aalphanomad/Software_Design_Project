package com.alphanomad.AN_Webapp;

public class CourseItem
{

	String course_code;
	String course_name;

	/**
	 * stores information about course allocations
	 * @param course_code
	 * @param course_name
	 */
	
	//Constructor for us to create more instances of objects of type CourseItem
	public CourseItem(String course_code, String course_name)
	{
		this.course_code = course_code;
		this.course_name = course_name;
	}

	public String getCourse_code()
	{
		return course_code;
	}


	public String getCourse_name()
	{
		return course_name;
	}

}
