package com.spay.module.member.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.spay.exception.SPayException;
import com.spay.module.member.dao.MemberDAO;
import com.spay.module.member.model.MemberUserDetails;

@Component
public class MemberBO implements UserDetailsService {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    MemberDAO memberDAO;	
    
    public void addMember(MemberUserDetails member) {
    	int result = memberDAO.insertMember(member);
        if (result != 1) {
            throw new SPayException(SPayMemberErrorCode.MEMBER_ADD_FAIL);
        }    	
    }
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberUserDetails member = memberDAO.selectMemberById(username);
        return member;
	}
	
    public UserDetails getMemberByName(String name) {
    	MemberUserDetails member = memberDAO.selectMemberById(name);
        return member;
    }	

}
