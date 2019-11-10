package com.alphanomad.AN_Webapp;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConfirmClaimFormTest {

	@Test
	public void testConfirmClaimForm() {
		String str1="Marubini";
		String str2="1622535";
		String str3="Software Design";
		String str4="LoginView";
		String str5="12-09-2019";
		String str6="MSL";
		String str7="08:00";
		String  str8="09:00";
		
		ConfirmClaimForm claimForm=new ConfirmClaimForm(str1, str2, str3, str4, str5, str6, str7, str8);
		
		
		
	}

}
