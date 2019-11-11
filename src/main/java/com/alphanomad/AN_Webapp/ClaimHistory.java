package com.alphanomad.AN_Webapp;

import java.util.ArrayList;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;

public class ClaimHistory extends VerticalLayout implements View
{

	public TextField Username;
	public PasswordField Password;
	public ConfirmClaimForm c;
//This function will correctly format the data received from the database and will add the data into a matrix, making it easy to populate
	//the grid
	public String[][] Display(String info, int size)
	{
		String[][] Matrix = new String[7][size];
		String[] first = info.split("\\],");
		String[] second = null;
		

		for (int i = 0; i < first.length; i++)
		{
			second = first[i].split(",");
			for (int j = 0; j < second.length; j++)
			{	
				String[] third = second[j].split("\":\"");
				Matrix[i][j] = third[1].substring(0, third[1].length() - 2);
			}
		}
		Matrix[6][second.length - 1] = Matrix[6][second.length - 1].substring(0,Matrix[6][second.length - 1].length() - 3);
		return Matrix;
	}

	@SuppressWarnings("unchecked")
	public ClaimHistory()
	{

	}

	/*@SuppressWarnings("unchecked")
	@Override
	public void enter(ViewChangeEvent event)
	{
		removeAllComponents();
		//Gets the username
		UserInfo tutor_info = ((MyUI) getUI()).get_user_info();
		String Tutor_Name = tutor_info.name;
		String Tutor_StudentNum = tutor_info.student_num;	
*/
}

