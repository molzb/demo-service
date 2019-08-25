package com.syniverse.demo.controller;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	private DateFormat df = new SimpleDateFormat("HH:mm:ss");

	@Value("${server.port}")
	private int serverPort;

	@Value("${management.server.port}")
	private int managementPort;

	@Autowired
	HttpServletRequest req;

	@GetMapping(value = "/", produces = TEXT_PLAIN_VALUE)
	public String showRoot() {
		return "Service is up and running";
	}

	@GetMapping(value = "/", produces = TEXT_PLAIN_VALUE, params = "debug=true")
	public String showRootOnPort() {
		String tpl = "Service is up and running on %s:%d, Management Port on %d";
		return String.format(tpl, req.getServerName(), serverPort, managementPort);
	}

	@GetMapping(value = "header", produces = TEXT_PLAIN_VALUE, headers = "hello=world")
	public String cannotBeEmbeddedOnAnotherPage() {
		return "Can be called with 'curl -H \"hello:world\" http://localhost:8888/header'";
	}

	@GetMapping("date")
	public String getDateAsHHmmss() {
		return df.format(new Date());
	}

	@GetMapping("add/{s1:[\\d]+}/{s2:[\\d]+}")
	public Long add(@PathVariable Long s1, @PathVariable Long s2) {
		return s1 + s2;
	}

	@GetMapping(value = "hello", produces = TEXT_PLAIN_VALUE)
	public String sayHello() {
		return "Hello World";
	}

	@GetMapping(value = "hello/{who}", produces = TEXT_PLAIN_VALUE)
	public String sayHelloWho(@PathVariable String who) {
		return "Hello " + who;
	}

	@GetMapping({ "list", "List", "Liste", "list/*" })
	public List<String> getList() {
		return Arrays.asList(new String[] { "this", "that" });
	}

	@GetMapping("downloadFile_{date}")
	public String getDate(@PathVariable long date) {
		return "A dummy file with a date: " + new Date(date).toString();
	}

	@GetMapping("sometimesThisWorks")
	public String dateOrDie() {
		if (Math.random() > 0.5)
			return new Date().toString();
		throw new RuntimeException("Sometimes it fails");
	}

	/**
	 * Parses a tap file name and splits it into its tokens
	 * 
	 * @param filename e.g. CDDEUD1NLDLT12345
	 * @return a List with 4 tokens: filetype, sender, receiver, sequence
	 *         number.<br>
	 *         Ex. Filename CDDEUD1NLDLT12345 should be split into
	 *         CD|DEUD1|NLDLT|12345
	 */
	public List<String> splitTapfileIdIntoTokens(String filename) {
		if (filename.length() != 17) {
			throw new IllegalArgumentException("The name of a tapfile has exactly 17 characters.");
		}
		String ft = filename.substring(0, 2);
		String sender = filename.substring(2, 7);
		String receiver = filename.substring(7, 12);
		String sequenceNr = filename.substring(12);
		boolean seqIsANumber = sequenceNr.matches("[\\d]{5}");
		if (!seqIsANumber) {
			throw new ParseException(12, sequenceNr + " is not a number.");
		}
		return List.of(ft, sender, receiver, sequenceNr);
	}

}
