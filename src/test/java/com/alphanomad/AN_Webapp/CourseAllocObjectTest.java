package com.alphanomad.AN_Webapp;

import static org.junit.Assert.*;

import org.junit.Test;

public class CourseAllocObjectTest {

	@Test
	public void test() {
		String name="Name";
		String id="1";
		String studNum="11";
		String role="2";
		String course="COMS3008 Parallel Computing";
		String confirmed="1";
		
		CourseAllocObject courseAllocObject=new CourseAllocObject(id,"marubini", studNum,role, course, confirmed);
		courseAllocObject.getClass();
		courseAllocObject.getConfirmed();
		courseAllocObject.getId();
		courseAllocObject.getCourse();
		courseAllocObject.getRole();
		courseAllocObject.getName();
		courseAllocObject.getStud_num();
		
		String role2="1";
		String role3="0";
		String cnf2="0";
		String cnf3="3";
		CourseAllocObject courseAllocObject2=new CourseAllocObject(id,"marubini", studNum,role2, course, cnf2);
		courseAllocObject2.getClass();
		courseAllocObject2.getConfirmed();
		courseAllocObject2.getId();
		courseAllocObject2.getCourse();
		courseAllocObject2.getRole();
		courseAllocObject2.getStud_num();
		
		
		CourseAllocObject courseAllocObject3=new CourseAllocObject(id,"marubini", studNum,role3, course, cnf3);
		courseAllocObject3.getClass();
		courseAllocObject3.getConfirmed();
		courseAllocObject3.getId();
		courseAllocObject3.getCourse();
		courseAllocObject3.getRole();
		courseAllocObject3.getStud_num();
		
		CourseAllocObject courseAllocObject4=new CourseAllocObject(id,"marubini", studNum,"3", course, "-1");
		courseAllocObject4.getRole();
		courseAllocObject4.getConfirmed();
		

	}

}
