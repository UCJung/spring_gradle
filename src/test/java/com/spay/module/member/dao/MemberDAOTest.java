package com.spay.module.member.dao;

import static org.hamcrest.CoreMatchers.is;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.spay.exception.SPayException;
import com.spay.module.member.model.Member;
import com.spay.module.member.model.MemberUserDetails;
import com.spay.test.base.DBTestBase;

public class MemberDAOTest extends DBTestBase {

	@Autowired
	MemberDAO memberDAO;
	
	private Map<String, MemberUserDetails > members = new HashMap<>();
	
	@Before
	public void setupUser() {
		MemberUserDetails member = new MemberUserDetails();
		member.setId("mykumi1");
		member.setName("woohcul");
		member.setPassword("test");
		members.put(member.getId(), member);
		
		member = new MemberUserDetails();
		member.setId("mykumi2");
		member.setName("woohcul2");
		member.setPassword("test");
		members.put(member.getId(), member);
		
		memberDAO.deleteMember(members.get("mykumi1"));
		memberDAO.deleteMember(members.get("mykumi2"));
	}
	
	@Test
	public void addUserTest() throws SPayException {
		assertThat(memberDAO.insertMember(members.get("mykumi1")), is(1));
		assertThat(memberDAO.insertMember(members.get("mykumi2")), is(1));
	}
	
	@Test
	public void deleteUserTest() throws SPayException {
		assertThat(memberDAO.insertMember(members.get("mykumi1")), is(1));
		assertThat(memberDAO.deleteMember(members.get("mykumi1")), is(1));
		assertThat(memberDAO.deleteMember(members.get("mykumi1")), is(0));
	}	
	
	@Test
	public void selectUserTest() throws SPayException {
		memberDAO.insertMember(members.get("mykumi1"));
		Member seletedMember = memberDAO.selectMemberById(members.get("mykumi1").getId());
		isSameMember(members.get("mykumi1"), seletedMember);
		isSameMember(members.get("mykumi1"), memberDAO.selectMemberByNo(seletedMember.getNo()));
		isSameMember(members.get("mykumi1"), memberDAO.selectMember(seletedMember));
		memberDAO.deleteMember(members.get("mykumi1"));		
	}

	private void isSameMember(Member seedMember, Member selectedMember) {
		assertThat(seedMember.getId(), is(seedMember.getId()));
		assertThat(seedMember.getName(), is(seedMember.getName()));
		assertThat(seedMember.getPassword(), is(seedMember.getPassword()));
	}	
	
	
}

