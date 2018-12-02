package com.syniverse.demo.jms;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syniverse.demo.model.Country;

@RestController
public class JmsSender {
	@Autowired
	private ApplicationContext ctx;

	private DateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	
	@GetMapping("jms")
	public String sendMessage() {
		System.out.println("........... sendMessage");
		JmsTemplate jmsTemplate = ctx.getBean(JmsTemplate.class);
		Country c = new Country("Taka", "Takatukaland " + sdf.format(new Date()));
		jmsTemplate.convertAndSend("jmsCountry", c);
		return "JMS message sent to " + jmsTemplate;
	}
}
