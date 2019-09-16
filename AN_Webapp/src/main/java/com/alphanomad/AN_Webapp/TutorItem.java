package com.alphanomad.AN_Webapp;

import com.vaadin.server.ExternalResource;

public class TutorItem
{
	// Set the stuff here
	ExternalResource Profile_Picture;

	String Name;
	String Student_num;

	public TutorItem(String Name, String Student_num)
	{
		this.Name = Name;
		this.Student_num = Student_num;

		this.Profile_Picture = new ExternalResource(
				"https://sophosnews.files.wordpress.com/2014/04/anonymous-250.jpg?w=100");

	}

	public ExternalResource getImage()
	{
		return Profile_Picture;
	}

	public String getName()
	{
		return Name;
	}

	public void setName(String Name)
	{
		this.Name = Name;
	}

	public String getStudent_num()
	{
		return Student_num;
	}

	public void setStudent_num(String Student_num)
	{
		this.Student_num = Student_num;
	}
}