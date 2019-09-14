package com.alphanomad.AN_Webapp;

public class UserInfo {
	String name = "error";
	String student_num = "error";
	String role = "error ";
	
	
	public UserInfo(String name, String student_number, String role) 
	{
		this.name = name;
		this.student_num = student_number;
		this.role = role;
		
		
	}
	
	public String get_name() {
		return name;
	}
	
	public String get_role() {
		return role;
	}
	
	public String get_student_num() {
		return student_num;
	}
}
