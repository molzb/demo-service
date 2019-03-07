package com.syniverse.demo;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix="info.app")
@Getter
@Setter
public class ConfigPropsDemo {
	private String name;
	private String description;
	private String version;
	private int build;
	
	private List<String> servers;
	
	@PostConstruct
	public void showInfoApp() {
		System.out.printf("\tApp according to config: %s, Build %d%n \t%s%n", name, build, description);
		System.out.println("\tServers according to config: " + servers == null ? "null" : servers.toString());
	}
}
