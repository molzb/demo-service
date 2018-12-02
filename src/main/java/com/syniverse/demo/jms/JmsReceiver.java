package com.syniverse.demo.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.syniverse.demo.model.Country;

@Component
public class JmsReceiver {
	@JmsListener(destination = "jmsCountry", containerFactory = "myFactory")
	public void receiveMessage(Country c) {
		System.out.println("Received <" + c + ">");
	}
}
