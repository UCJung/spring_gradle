package com.spay.test.base;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.spay.config.AppTestConfig;
import com.spay.config.DataConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
		classes={ AppTestConfig.class, DataConfig.class })
@Transactional
@Rollback(false)
public class DBTestBase extends Assert {
	
	protected final Logger logger = LoggerFactory.getLogger(DBTestBase.class);
	
}
