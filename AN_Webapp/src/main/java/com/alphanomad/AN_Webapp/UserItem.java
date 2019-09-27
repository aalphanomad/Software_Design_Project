package com.alphanomad.AN_Webapp;

public class UserItem
{
	// Set the stuff here
	String name;
	String student_num;
	String role;

	/**
	 * similar to user info just stores some things about the user
	 * @param name
	 * @param student_num
	 * @param role
	 */
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

	public String getStudent_num()
	{
		return student_num;
	}

	public String getRole()
	{
		return role;
	}
}
