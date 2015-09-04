package com.spay.web.config;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.jasypt.springsecurity3.authentication.encoding.PasswordEncoder;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.spay.config.AppTestConfig;
import com.spay.config.DataConfig;
import com.spay.member.bo.MemberBOTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
		classes={ AppTestConfig.class, DataConfig.class, SpringSecutiryConfig.class })
public class SpringSecutiryConfigTest {

	static final Logger logger = LoggerFactory.getLogger(MemberBOTest.class);
	
	@Autowired PasswordEncoder passwordEncoder;
	@Test
	public void isValidPasswordTest() {
		passwordEncoder.setPasswordEncryptor(new StrongPasswordEncryptor());
		
		String salt = "a";
		String password = "123456";
		String encPassword = passwordEncoder.encodePassword(password, salt);
		
		assertThat(passwordEncoder.isPasswordValid(encPassword, password, salt), is(true));
	}

}
