package com.syniverse.demo.config;

import java.awt.Button;
import java.awt.Point;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

@Configuration
@ImportResource({"classpath:applicationContext.xml"})
@Component
public class ConfigXml {
	ConfigXml() {
		System.setProperty("java.awt.headless", "false");	// Button crashes, if I don't do that
	}
	
	// No qualifier needed, because the names are the same in Java and XML
	@Autowired
	private String canWeUseBothXmlAndProperties;
	@Autowired
	private String stringInBeansXml;
	@Autowired
	private String additionalXml;
	
	@Autowired
	@Qualifier("qualifierDemo")
	private String qualifierNeeded;
	
	@Autowired
	private Point xmasPoint;
	
	@Autowired
	private Button xmasButton;

	@Autowired
	private Button xmasButtonAlias;

	@PostConstruct
	private void showXmlBean() {
		System.out.println("\t-------------------------------------");
		System.out.println("\tCan we use both XML and properties? " + canWeUseBothXmlAndProperties);
		System.out.println("\tValue of XML Bean: " + stringInBeansXml);
		System.out.println("\tAnother XML: " + additionalXml);
		System.out.println("\tExample with qualifier: " + qualifierNeeded);
		System.out.println("\tExample with constructor: " + xmasPoint);
		System.out.println("\tExample with properties: "  + xmasButton);
		System.out.println("\tThe same object as 'alias': "  + xmasButtonAlias);
		System.out.println("\t-------------------------------------");
	}
}
