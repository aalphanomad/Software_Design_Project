package com.alphanomad.AN_Webapp;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClaimFormTest {

	@Test
	public void testClaimForm() {
		ClaimForm claimForm=new ClaimForm();
		String FromDb="{\"result\":[{\"COURSE_1\":\"COMS3007\",\"COURSE_2\":\"COMS2015\",\"COURSE_3\":\"COMS1017\",\"COURSE_4\":null,\"COURSE_5\":null}]}";
		claimForm.GetCourses(FromDb);
		
		String course_code="COMS1015";
		claimForm.Course_corr(course_code);
		
		String course_code1="COMS1018";
		claimForm.Course_corr(course_code1);
		
		String course_code2="COMS1017";
		claimForm.Course_corr(course_code2);
		
		
		String course_code3="COMS1016";
		claimForm.Course_corr(course_code3);
		
		String course_code4="COMS2002";
		claimForm.Course_corr(course_code4);
		
		String course_code5="COMS2013";
		claimForm.Course_corr(course_code5);
		
		String course_code6="COMS2014";
		claimForm.Course_corr(course_code6);
		
		String course_code7="COMS2015";
		claimForm.Course_corr(course_code7);
		
		String course_code8="COMS3002";
		
		//claimForm.getAllInfo();
		
	}
	
	

}
