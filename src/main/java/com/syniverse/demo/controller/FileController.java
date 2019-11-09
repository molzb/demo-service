package com.syniverse.demo.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_PDF_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping(value = "raex", produces = APPLICATION_XML_VALUE)
	public String getRaex() {
		String userHome = System.getProperty("user.home");
		String f = userHome + "\\eclipse-workspace\\demo-service\\src\\main\\resources\\raexIot.xml";
		String xmlContent = utils.loadFile(f);
		return xmlContent; // XML.toJSONObject(xmlContent).toString(2);
	}

	@GetMapping(value = "raexAsJson", produces = APPLICATION_JSON_VALUE)
	public String getRaexAsJson() throws IOException {
		XmlMapper mapper = new XmlMapper();
		JsonNode jsonNode = mapper.readTree(getResourceInputStream("/raexIot.xml"));
		ObjectMapper objMapper = new ObjectMapper();
		return objMapper.writeValueAsString(jsonNode);
	}

	@GetMapping(value = "downloadPdf", produces = APPLICATION_PDF_VALUE)
	public byte[] getPdf() throws IOException {
		InputStream stream = getResourceInputStream("/Bash.pdf");
		return stream.readAllBytes();
	}

	InputStream getResourceInputStream(String filename) {
		return getClass().getResourceAsStream(filename);
	}

}
