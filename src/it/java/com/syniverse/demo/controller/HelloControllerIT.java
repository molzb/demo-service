package com.syniverse.demo.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.syniverse.demo.controller.HelloController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloControllerIT {
	@Autowired
	private HelloController h;

	@Test
	public void testSayHello() {
		assertEquals("Hello World", h.sayHello());
	}
	
	@Test
	public void testSayHelloWho() {
		assertEquals("Hello Who?", h.sayHelloWho("Who?"));
	}

}
