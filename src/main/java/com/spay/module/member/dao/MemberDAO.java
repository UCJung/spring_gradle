package com.spay.module.member.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.spay.module.member.model.Member;

@Repository
public interface MemberDAO {

	int insertMember(Member member);

	Member selectMemberByName(@Param("name") String name);
	
	void selectSessionKey(HashMap<String, Object> param);

}
