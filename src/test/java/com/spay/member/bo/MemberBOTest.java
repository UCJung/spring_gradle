package com.spay.member.bo;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import com.spay.module.member.bo.MemberBO;
import com.spay.test.base.BoTestBase;

public class MemberBOTest extends BoTestBase {
	
	@Autowired
	MemberBO memberBO;	
	
	@Test
	public void test2() {
		UserDetails member = memberBO.getMemberByName("mkyong");
		assertThat(member.getUsername(),is("mkyong"));
	}	

}
