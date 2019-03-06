package com.syniverse.demo;

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
	
	@PostConstruct
	public void showInfoApp() {
		System.out.println(name + "\n" + description);
	}
}
