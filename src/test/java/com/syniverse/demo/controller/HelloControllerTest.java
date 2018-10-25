package com.syniverse.demo.controller;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class HelloControllerTest {
	private HelloController h = new HelloController();

	@Test
	public void testSayHello() {
		assertEquals("Hello World", h.sayHello());
	}

	@Test
	public void testSayHelloWho() {
		assertEquals("Hello Who?", h.sayHelloWho("Who?"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSplitTapfileIntoTokensException() {
		h.splitTapfileIdIntoTokens("CDDEUD1NLDLT12345_TOO_LONG");
	}

	@Test(timeout = 999L)
	public void testSplitTapfileIntoTokensTimeout() {
		h.splitTapfileIdIntoTokens("CDDEUD1NLDLT12345");
	}

	@Test
	public void testSplitTapfileIntoTokens() {
		List<String> tokens = h.splitTapfileIdIntoTokens("CDDEUD1NLDLT12345");
		int expectedLength = 4;
		assertEquals("Invalid number of tokens in CDDEUD1NLDLT12345 ", expectedLength, tokens.size());
	}
}
