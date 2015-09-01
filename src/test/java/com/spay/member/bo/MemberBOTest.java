package com.spay.member.bo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DefaultBootstrapContext;
import org.springframework.transaction.annotation.Transactional;

import com.spay.member.dao.MemberDAO;
import com.spay.member.model.Member;
import com.spay.web.config.DataConfig;
import com.spay.web.config.WebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
@Transactional
public class MemberBOTest {

	@Autowired
	MemberDAO memberDAO;
	
	@Test
	public void test() {
		Member member = memberDAO.selectMemberByName("mkyong");
	}

}
