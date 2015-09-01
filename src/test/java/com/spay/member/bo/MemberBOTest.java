package com.spay.member.bo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.spay.web.config.AppTestConfig;
import com.spay.web.config.DataConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
		classes={ AppTestConfig.class, DataConfig.class })
@Transactional
public class MemberBOTest {
	
	static final Logger logger = LoggerFactory.getLogger(MemberBOTest.class);
	
	@Autowired
	MemberBO memberBO;	
	
	@Test
	public void test2() {
		UserDetails member2 = memberBO.getMemberByName("mkyong");
		logger.info(member2.getUsername());
		
		System.out.println(member2.getUsername());
	}	

}
