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

		//Below,we are trying to get the number of bookings
		DBHelper dbh = new DBHelper();
		String[] params = { "name", "student_num" };
		String[] values = { Tutor_Name, Tutor_StudentNum };
		//Output is just a single integer specifying the number of bookings

		int size = Integer.parseInt(dbh.php_request("no_of_bookings", params, values));
		
		//Gets all our past claims
		//An Example output is {"dates":[{"DATE":"05 Apr 2019"}],"courses":[{"COURSE":"COMS2014"}],"start_time":[{"START_TIME":"08:51:00"}],
		//"end_time":[{"END_TIME":"08:58:00"}],"valid":[{"VALID":"0"}],"venue":[{"VENUE":"hehe"}],"activity":[{"ACTIVITY":"Invigilating"}]}
		
		String ans = dbh.php_request("fetching", params, values);
		if(!dbh.parse_json_string(ans).get("dates").toString().equals("[]")) {
		String[][] Test = Display(ans, size);
		Label test = new Label("<p style = \"font-family:georgia,garamond,serif;font-size:30px;\">\r\n"
				+ "       <b><u>Your Claim History</u></b> " + "      </p>", ContentMode.HTML);
		addComponent(test);
		ArrayList<HistoryItem> History = new ArrayList<>();
		
*/
}

