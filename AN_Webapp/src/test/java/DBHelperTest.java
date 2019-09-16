//package com.alphanomad.AN_Webapp;
import com.alphanomad.AN_Webapp.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gson.JsonObject;

public class DBHelperTest {

	@Test
	public void testDBHelper() {
		DBHelper dbHelper=new DBHelper();
		String[] params = {"student_num"} ;
    	String[] values = {"1622535"};
    	String endpoint="signin";
    	
    	dbHelper.php_request(endpoint, params, values);
    	
    	String[] params1 = {"student_num"} ;
    	String[] values1 = {"15"};
    	String endpoint1="in";
    	dbHelper.php_request(endpoint1, params1, values1);
    	
    	String w="123svevrb eedvevgbg";
    	//JsonObject g=dbHelper.parse_json_string(w);
    	
    	
    	
	}

}
