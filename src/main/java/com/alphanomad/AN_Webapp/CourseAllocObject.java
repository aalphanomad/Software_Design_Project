package com.alphanomad.AN_Webapp;

public class CourseAllocObject
{
	String id;
	String name;
	String stud_num;
	String course;
	String role;
	String confirmed;

	/**
	 * like CourseItem but it has confirmed as well
	 * @param stud_num
	 * @param course
	 * @param confirmed
	 */
	public CourseAllocObject(String id,String name,String stud_num,String role, String course, String confirmed)
	{	this.id=id;
		this.name=name;
		this.stud_num = stud_num;
		this.course = course;
		this.role=role;
		this.confirmed = confirmed;
	}

	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getStud_num()
	{
		return stud_num;
	}

	public String getCourse()
	{
		return course;
	}
	public String getRole() {
		if(role.equals("0")) {
			return "Tutor";
		}
		if(role.equals("1")) {
			return "Lecturer";
		}
		if(role.equals("2")) {
			return "Admin";
		}
		else {
			return "Super Admin";
		}
	}

	public String getConfirmed()
	{
		if (confirmed.equals("1"))
		{
			return "Accepted";
		}
		else if (confirmed.equals("0"))
		{
			return "Pending";
		}
		else if (confirmed.equals("-1"))
		{
			return "Declined";
		}
		
		// this should never happen but if it does it'll be a sure sign of error
		return confirmed;
	}
}
