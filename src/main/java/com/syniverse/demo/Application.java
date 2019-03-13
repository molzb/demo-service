package com.syniverse.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.syniverse.demo.config.ConfigWithPrefixInfoApp;

@SpringBootApplication
@Import(ConfigWithPrefixInfoApp.class)
@EnableAspectJAutoProxy
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
//	@Bean
//	public CommandLineRunner executeCommandLineRunner(ApplicationContext ctx) {
//		return (String... args) -> {
//			String[] beanNames = ctx.getBeanDefinitionNames();
//			Arrays.sort(beanNames);
//			for (String beanName : beanNames) {
//				System.out.println(beanName);
//			}
//		};
//	}
}
