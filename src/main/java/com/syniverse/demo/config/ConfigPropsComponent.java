package com.syniverse.demo.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value="classpath:additional.properties")
public class ConfigPropsComponent {
	@Autowired
	@Qualifier("appname")
	private String appname;
	
	@Value("${earth.radius}")
	private String earthRadius;
	@Value("${earth.diameter}")
	private String earthDiameter;
	@Value("${earth.moons}")
	private String earthMoons;

	@PostConstruct
	private void showAppname() {
		System.out.printf("%s uses Singleton Bean 'appname': %s%n", getClass().getSimpleName(), appname);
		System.out.println("These values come from additional.properties:");
		System.out.printf("\t Earth radius:%s, diameter:%s, moons:%s%n", earthRadius, earthDiameter, earthMoons);
	}

}
