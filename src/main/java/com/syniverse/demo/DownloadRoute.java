package com.syniverse.demo;

import java.net.ConnectException;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Camel Route: Downloads localhost/date to file:target/date every 60s
 * 
 * @author Bernhard
 */
@Component
public class DownloadRoute extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		onException(ConnectException.class).log("Server seems to be down").handled(true).to("direct:server-error");
		// handled(true) -> loggt keine Zusammenfassung mit Endpoints
		String timer1 = "timer://foo?fixedRate=true&period=60000";
		String url = "http://localhost:8888/date";
		String file = "file:target/date";
		from(timer1).to(url).to(file); // do that, if I don't use logging
//		from(timer1).to(url).convertBodyTo(String.class).log("${id}->${body}").to(file); // with logging
		from("direct:server-error").log("Server error. " + getContext().getEndpoints()).transform()
				.simple("Msg: ${exception.message}").to("stream:out");
	}
}
