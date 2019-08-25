package com.syniverse.demo.controller;

import static org.springframework.http.MediaType.APPLICATION_PDF_VALUE;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.syniverse.demo.util.Utils;

@RestController
public class FileController {

	@Autowired
	private Utils utils;

	@GetMapping(value = "raex", produces = MediaType.APPLICATION_XML_VALUE)
	public String getRaex() {
		String userHome = System.getProperty("user.home");
		String f = userHome + "\\eclipse-workspace\\demo-service\\src\\main\\resources\\raexIot.xml";
		String xmlContent = utils.loadFile(f);
		return xmlContent; // XML.toJSONObject(xmlContent).toString(2);
	}

	@GetMapping(value = "raexAsJson", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getRaexAsJson() throws IOException {
		String userHome = System.getProperty("user.home");
		String f = userHome + "\\eclipse-workspace\\demo-service\\src\\main\\resources\\raexIot.xml";
		String xmlContent = utils.loadFile(f);
		XmlMapper mapper = new XmlMapper();
		JsonNode jsonNode = mapper.readTree(xmlContent.getBytes(Charset.defaultCharset()));
		ObjectMapper objMapper = new ObjectMapper();
		return objMapper.writeValueAsString(jsonNode);
	}

	@GetMapping(value = "downloadPdf", produces = APPLICATION_PDF_VALUE)
	public byte[] getPdf() throws IOException {
		InputStream stream = getClass().getResourceAsStream("/Bash.pdf");
		return stream.readAllBytes();
	}

}
