package com.alphanomad.AN_Webapp;

import java.util.ArrayList;
import java.util.Arrays;

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

	public String[][] Display(String info, int size)
	{
		String[][] Matrix = new String[7][size];
		String[] first = info.split("\\],");
		String[] second = null;
		

		for (int i = 0; i < first.length; i++)
		{
			//addComponent(new Label(Integer.toString(i)));

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

	@SuppressWarnings("unchecked")
	@Override
	public void enter(ViewChangeEvent event)
	{
		removeAllComponents();

		UserInfo tutor_info = ((MyUI) getUI()).get_user_info();
		String Tutor_Name = tutor_info.name;
		String Tutor_StudentNum = tutor_info.student_num;

		DBHelper dbh = new DBHelper();
		String[] params = { "name", "student_num" };
		String[] values = { Tutor_Name, Tutor_StudentNum };

		int size = Integer.parseInt(dbh.php_request("no_of_bookings", params, values));
		String ans = dbh.php_request("fetching", params, values);

		String[][] Test = Display(ans, size);

		Label test = new Label("<p style = \"font-family:georgia,garamond,serif;font-size:30px;\">\r\n"
				+ "       <b><u>Your Claim History</u></b> " + "      </p>", ContentMode.HTML);
		addComponent(test);
		ArrayList<HistoryItem> History = new ArrayList<>();
		String[] info = new String[8];
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < 7; j++)
			{

				info[j] = Test[j][i];
			}
			System.out.println(info[4]);
			if(info[4].equals("0"))
			History.add(new HistoryItem(info[0], info[1], info[2], info[3], "Not Validated", info[5], info[6]));
			else {
				History.add(new HistoryItem(info[0], info[1], info[2], info[3], "Validated", info[5], info[6]));

			}
		}
ButtonRenderer dummy;
		Grid<HistoryItem> grid = new Grid<>(HistoryItem.class);
		grid.setItems(History);
		grid.addColumn(unused -> "Validate",
				
				
				dummy= new ButtonRenderer(
				
			
				e ->
				{
					if((((HistoryItem) e.getItem()).getValid()).equals("1")) {
						Notification.show("This Claim Has Already Been Validated!");
					}
					else {
					removeAllComponents();

					addComponent(c = new ConfirmClaimForm(Tutor_Name, Tutor_StudentNum,
							(((HistoryItem) e.getItem()).getCourse()), (((HistoryItem) e.getItem()).getActivity()),
							(((HistoryItem) e.getItem()).getVenue()), (((HistoryItem) e.getItem()).getDate()),
							(((HistoryItem) e.getItem()).getStart_Time()),
							(((HistoryItem) e.getItem()).getEnd_time())));
				}
				}
				

				)); 

		grid.setWidth("100%");
		grid.setHeightUndefined();
		// grid.setStyleName(style); 
		addComponent(grid);
		
	
	Button home=new Button("Home Screen",e->getUI().getNavigator().navigateTo("tutormain"));
	
	addComponent(home);


}
}

