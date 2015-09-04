package com.spay.module.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.spay.module.member.model.Member;
import com.spay.module.member.model.MemberUserDetails;

@Repository
public interface MemberDAO {

	int insertMember(MemberUserDetails member);
	
	MemberUserDetails selectMemberByNo(@Param("no") long no);
	
	List<MemberUserDetails> getMembers();
	
	int deleteMember(MemberUserDetails member);

	MemberUserDetails selectMemberById(@Param("id") String id);

	MemberUserDetails selectMember(Member member);

}
