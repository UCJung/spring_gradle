package com.spay.web.config;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class DataConfigTest {
	final Logger log = LoggerFactory.getLogger(DataConfigTest.class);
	
	@Test
	public void test() {
		JdbcTemplate template = new JdbcTemplate(new DataConfig().dataSource());
		List<Map<String, Object>> rowCount = template.queryForList("select * from Categories");
		
		log.info("count : " + rowCount.size());
		//assertThat()
	}

}
