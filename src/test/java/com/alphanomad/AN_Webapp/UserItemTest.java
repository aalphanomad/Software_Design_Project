package com.alphanomad.AN_Webapp;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserItemTest {

	@Test
	public void test() {
		String name="MM";
		String student_num="1622";
		String role="0";
		String role2="1";
		String role3="2";
		String role4="3";
		UserItem userItem=new UserItem(name, student_num, role);
		userItem.getName();
		userItem.getStudent_num();
		userItem.getRole();
		
		UserItem userItem2=new UserItem(name,student_num,role2);
		userItem.getRole();
		
		UserItem userItem3=new UserItem(name,student_num,role3);
		userItem3.getRole();

		UserItem userItem4=new UserItem(name,student_num,role4);
		userItem4.getRole();

	}

}
