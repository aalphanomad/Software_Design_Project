package com.alphanomad.AN_Webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.ImageRenderer;

public class LectMainView extends VerticalLayout implements View
{
	//A JsonObject to stre the return alue from the database
	JsonObject retrieve;
	int SelIndex;
	ArrayList<TutorItem> Tutor = new ArrayList<TutorItem>();
	//Filters the data received from the database as it currently contains [],{},and inverted commas
	
	 	ArrayList<String> myCourses;
	
	public static ArrayList<String> Filter_Info(String Dirty_Info)
	{
		ArrayList<String> final_Array = new ArrayList<String>();

		String[] test = Dirty_Info.split(":");
		if (test[1].length() == 1 || test[1].substring(0, 3).equals("[]}"))
		{
			//final_Array.add("-10");
			return final_Array;
		} else
		{
			String[] filtered_Array1 = Dirty_Info.split(":");
			filtered_Array1[1] = filtered_Array1[1].substring(1, filtered_Array1[1].length());

			String[] filtered_Array2 = filtered_Array1[1].split(",");
			for (int i = 0; i < filtered_Array2.length; i++)
			{
				if (!filtered_Array2[i].equals("null"))
				{
					final_Array.add(filtered_Array2[i].substring(1, filtered_Array2[i].length() - 1));
				}
			}
			return final_Array;
		}
	}

	public LectMainView()
	{

	}

	/*@Override
	public void enter(ViewChangeEvent vc_event)
	{
		if (! ((MyUI)getUI()).logged_in)
		{
			getUI().getNavigator().navigateTo("login");
			return;
		} 
		Tutor.clear();

		
		//Get the details of the currently logged in lecturer
		UserInfo lect_info = ((MyUI) getUI()).get_user_info();
		
		removeAllComponents();


	}*/

}
