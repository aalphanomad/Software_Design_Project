package com.alphanomad.AN_Webapp;

public class CourseAllocObject
{
	String stud_num;
	String course;
	String confirmed;

	/**
	 * like CourseItem but it has confirmed as well
	 * @param stud_num
	 * @param course
	 * @param confirmed
	 */
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

	public String getCourse()
	{
		return course;
	}

	public String getConfirmed()
	{
		return confirmed;
	}
}
