package com.syniverse.demo.jms;

import java.util.Enumeration;

import javax.annotation.PostConstruct;
import javax.jms.QueueBrowser;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsMonitor {
	@Autowired
	JmsTemplate jmsTpl;

	@PostConstruct
	private void showJmsTemplate() {
		System.out.println("JMS Template: " + jmsTpl);

		jmsTpl.convertAndSend("mailbox", new Email("info@example.com", "Hello"));
		jmsTpl.browse("mailbox", (Session session, QueueBrowser browser) -> {
			System.out.printf("JMS Session: %s%nJMS browser: %s %n", session.toString(), browser.toString());
			Enumeration<?> messages = browser.getEnumeration();
			while (messages.hasMoreElements()) {
				System.out.println("\tMessage: " + messages.nextElement());
			}
			return null;
		});
	}
}
