package com.spay.member.bo;

import java.util.HashMap;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.spay.exception.SPayException;
import com.spay.member.dao.MemberDAO;
import com.spay.member.model.Member;
import com.spay.utils.DateUtils;
import com.spay.utils.enums.DatePatternEnum;

@Component
public class MemberBO implements UserDetailsService {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    MemberDAO memberDAO;	
    
    public void addMember(Member member) {
    	int result = memberDAO.insertMember(member);
        if (result != 1) {
            throw new SPayException(SPayMemberErrorCode.MEMBER_ADD_FAIL);
        }    	
    }
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberDAO.selectMemberByName(username);
        return member;
	}
	
    public UserDetails getMemberByName(String name) {
        Member member = memberDAO.selectMemberByName(name);
        return member;
    }	

    public String getSessionKey() {
        HashMap<String, Object> param = new HashMap<String, Object>();
        memberDAO.selectSessionKey(param);
        String sessionKey = ((Long)param.get("sessionKey")).toString();
        StringBuilder sb = new StringBuilder("SESSION_KEY");
        sb.append(":");
        sb.append(DateUtils.getToday(DatePatternEnum.YYYY_MM_DD));
        sb.append(":");
        sb.append(sessionKey);
        return sb.toString();
    }	
}
