package com.syniverse.demo.jms;

import java.awt.Point;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	@JmsListener(destination = "mailbox", containerFactory = "myFactory")
	public void receiveMessage(Email email) {
		System.out.println("Received <" + email + ">");
	}
	
	@JmsListener(destination = "x11", containerFactory = "myFactory")
	public void receiveMessage(Point p) {
		System.out.println("Received point <" + p + ">");
	}

}