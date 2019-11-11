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
	//Allows us to create instances of objects of type UserItem
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
		if (role.equals("0"))
		{
			return "Tutor";
		}
		else if (role.equals("1"))
		{
			return "Lecturer";
		}
		else if (role.equals("2"))
		{
			return "Admin";
		}
		else if (role.equals("3"))
		{
			return "Lecturer/Admin";
		}
		return role;
	}
}
