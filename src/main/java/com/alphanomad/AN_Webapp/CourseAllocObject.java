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
		if (confirmed.equals("1"))
		{
			return "accepted";
		}
		else if (confirmed.equals("0"))
		{
			return "pending";
		}
		else if (confirmed.equals("3"))
		{
			return "not accepted";
		}
		
		// this should never happen but if it does it'll be a sure sign of error
		return confirmed;
	}
}
