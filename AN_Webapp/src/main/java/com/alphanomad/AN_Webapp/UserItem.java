package com.alphanomad.AN_Webapp;

public class UserItem
{
	// Set the stuff here
	String name;
	String student_num;
	String role;

	public UserItem(String name, String student_num, String role)
	{
		this.name = name;
		this.student_num = student_num;
		this.role = role;

	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getStudent_num()
	{
		return student_num;
	}

	public void setStudent_num(String student_num)
	{
		this.student_num = student_num;
	}

	public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

}
