package com.syniverse.demo;

import java.net.ConnectException;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DownloadRoute extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		System.out.println("----------- configure DownloadRoute");
		onException(ConnectException.class).log("Server seems to be down").handled(true).to("direct:server-error");
		// handled(true) -> loggt keine Zusammenfassung mit Endpoints
		String timer1 = "timer://foo?fixedRate=true&period=10000";
		String url = "http://localhost:8888/date";
		String file = "file:target/date";
		from(timer1).to(url).to(file);	// Wenn ohne Logging
//		from(timer1).to(url).convertBodyTo(String.class).log("${id}->${body}").to(file);	// mit Logging
		from("direct:server-error").log("Server error. " + getContext().getEndpoints()).transform()
				.simple("Msg: ${exception.message}").to("stream:out");
	}
}
