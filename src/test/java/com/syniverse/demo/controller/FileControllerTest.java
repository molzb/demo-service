package com.syniverse.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
	}

	@Test
	public void testGetRaexAsJson() {
		String expectedJson = "{\"raex\": {\"Header\": {\"Submission\": \"1999-09-09\"}}}";
		when(utils.loadFile(anyString())).thenReturn(xml);
		assertEquals(expectedJson, ctrl.getRaexAsJson());
	}

	@Test
	public void testGetPdf() throws IOException {
		assertEquals(48672, ctrl.getPdf().length);
	}
}
