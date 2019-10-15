package com.alphanomad.AN_Webapp;

import com.google.gson.JsonObject;

public class UserInfo
{
	String name = "error";
	String student_num = "error";
	String role = "error ";

	public UserInfo(String name, String student_number, String role)
	{
		this.name = name;
		this.student_num = student_number;
		this.role = role;
	}

	/**
	 * makes a user info object using only the student number
	 * 
	 * @param student_number
	 */
	public UserInfo(String student_number)
	{
		this.student_num = student_number;
		String[] params = { "student_num", };
		String[] values = { student_num };
		DBHelper dbh = new DBHelper();
		String ans = dbh.php_request("get_user_info", params, values);

	}

	public String get_name()
	{
		return name;
	}

	public String get_role()
	{
		return role;
	}

	public String get_student_num()
	{
		return student_num;
	}

	public UserInfo copy()
	{
		return new UserInfo(name, student_num, role);
	}
}