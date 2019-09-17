//package com.alphanomad.AN_Webapp;
import com.alphanomad.AN_Webapp.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import com.vaadin.navigator.View;
public class LectMainViewTest {

	@Test
	public void testFilter_Info() {
		LectMainView lectMainView=new LectMainView();
		String dirt="123:123:123";
		String[] filtered_Array1=dirt.split(":");
    	filtered_Array1[1]=filtered_Array1[1].substring(1, filtered_Array1[1].length());

    	String[] filtered_Array2=filtered_Array1[1].split(",");
    	ArrayList<String>final_Array=new ArrayList<String>();
    	for(int i=0;i<filtered_Array2.length;i++) {
    		if(!filtered_Array2[i].equals("null")) {
    			final_Array.add(filtered_Array2[i].substring(1,filtered_Array2[i].length()-1));
    		}
    	}
		
		assertEquals(final_Array,lectMainView.Filter_Info(dirt) );
	
		
		//View old_view = new View();
		
	}

}
