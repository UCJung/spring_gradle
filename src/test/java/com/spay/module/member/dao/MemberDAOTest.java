package com.spay.module.member.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.spay.module.member.model.Member;
import com.spay.test.base.DBTestBase;

public class MemberDAOTest extends DBTestBase {

	@Autowired
	MemberDAO memberDAO;
	
	private Map<String, Member> members = new HashMap<>();
	
	@Before
	public void setupUser() {
		members.put("mykumi", new Member("mykumi", "test"));
		members.put("mykumi", new Member("mykumi2", "test"));
	}
	
	@Test
	public void addUserTest() {
		for(Entry<String, Member> entry : members.entrySet() ) {
			memberDAO.addMember(entry.getValue());
		}		
	
		List<Member> getMembers = memberDAO.getMembers();
		assertThat(members.size(), is(getMembers.size()));
	}

}
