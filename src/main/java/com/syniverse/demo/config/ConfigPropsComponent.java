package com.syniverse.demo.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ConfigPropsComponent {
	@Autowired
	@Qualifier("appname")
	private String appname;

	@PostConstruct
	private void showAppname() {
		System.out.printf("%s uses Singleton Bean 'appname': %s%n", getClass().getSimpleName(), appname);
	}

}
