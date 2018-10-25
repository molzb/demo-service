package com.syniverse.demo.util;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadUrl {
	@RequestMapping(value = "/readUrl", produces = TEXT_PLAIN_VALUE)
	public String readUrl(@RequestParam String url) throws IOException {
		URL myUrl = new URL(url);
		URLConnection conn = myUrl.openConnection();
		String pageText;
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), UTF_8))) {
		    pageText = reader.lines().collect(Collectors.joining("\n"));
		}
		return pageText;
	}
}
