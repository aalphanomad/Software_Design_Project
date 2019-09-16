package com.alphanomad.AN_Webapp;

public class CourseAllocObject
{
	String stud_num;
	String course;
	String confirmed;
	
	public CourseAllocObject(String stud_num, String course, String confirmed)
	{
		this.stud_num = stud_num;
		this.course = course;
		this.confirmed = confirmed;
	}
	
	public String getStud_num()
	{
		return stud_num;
	}

	public void setStud_num(String stud_num)
	{
		this.stud_num = stud_num;
	}

	public String getCourse()
	{
		return course;
	}

	public void setCourse(String course)
	{
		this.course = course;
	}

	public String getConfirmed()
	{
		return confirmed;
	}

	public void setConfirmed(String confirmed)
	{
		this.confirmed = confirmed;
	}
}
