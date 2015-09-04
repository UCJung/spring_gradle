package com.spay.web.config;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import com.spay.test.base.DefaultTestBase;

public class PropertiesConfigTest extends DefaultTestBase {

    private @Value("${jdbc.minimumIdle}") int minimumIdle;
    
	@Test
	public void test() {
		assertThat(minimumIdle, is(2));
	}

}
