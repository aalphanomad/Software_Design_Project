//package com.alphanomad.AN_Webapp;
import com.alphanomad.AN_Webapp.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class TutorItemTest {

	@Test
	public void testTutorItem() {
		String Name="Marubini";
		String Student_num="1";
		TutorItem tutorItem=new TutorItem(Name, Student_num);
		tutorItem.getName();
		tutorItem.setName(Name);
		tutorItem.getStudent_num();
		tutorItem.setStudent_num(Student_num);
	}

}
