package com.syniverse.demo.controller;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.syniverse.demo.util.Utils;

@RunWith(MockitoJUnitRunner.class)
public class FileControllerTest {

	@InjectMocks
	private FileController ctrl;

	@Mock
	private Utils utils;

	private String xml = "<raex><Header><Submission>1999-09-09</Submission></Header></raex>";

	@Test
	public void testGetRaex() {
		when(utils.loadFile(anyString())).thenReturn(xml);
		assertEquals(xml, ctrl.getRaex());
		verify(utils, atLeastOnce()).loadFile(anyString());
		verify(utils, timeout(100)).loadFile(anyString());
	}

	@Test
	public void testGetRaexAsJson() throws IOException {
		String expectedJson = "{\"Header\":{\"Submission\":\"1999-09-09\"}}";
		InputStream is = new ByteArrayInputStream(xml.getBytes(UTF_8));
		FileController fc = new FileController();
		FileController spy = Mockito.spy(fc);
		Mockito.doReturn(is).when(spy).getResourceInputStream(anyString());
		assertEquals(expectedJson, spy.getRaexAsJson());
	}

	@Test
	public void testGetPdf() throws IOException {
		int expectedPdfLength = 48672;
		assertEquals(expectedPdfLength, ctrl.getPdf().length);
	}
}
