//package com.alphanomad.AN_Webapp;
import com.alphanomad.AN_Webapp.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class UserInfoTest {

	@Test
	public void testUserInfo() {
		
		String name="Marubini";
		String studNum="1622535";
		String role="student";
		
		UserInfo userInfo=new UserInfo(name, studNum, role);
		assertEquals(name, userInfo.get_name());
		assertEquals(role, userInfo.get_role());
		assertEquals(studNum, userInfo.get_student_num());
	}

}
