package com.syniverse.demo.config;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.syniverse.demo.Application;
import com.syniverse.demo.config.ConfigPropsDemo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@TestPropertySource("classpath:/application-test.properties")
public class ConfigPropsDemoTest {
	@Autowired
	ConfigPropsDemo cfg;
	
	@Test
	public void testConfigurationProperties() {
		assertTrue(cfg.getName().startsWith("Test"));
	}

}
