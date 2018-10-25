package com.syniverse.demo.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class Utils {

	private static Logger logger = LoggerFactory.getLogger(Utils.class);
	
	@Cacheable("sqlFiles")
	public String loadSqlFile(String sqlFilename) {
		try {
			URI uri = getClass().getResource("/com/syniverse/report/controller/" + sqlFilename).toURI();
			byte[] bytes = Files.readAllBytes(Paths.get(uri));
			return new String(bytes, Charset.forName("UTF-8"));
		} catch (IOException | URISyntaxException e) {
			logger.error(e.getMessage(), e);
		}
		return "";
	}

	@Cacheable("files")
	public String loadFile(String sqlFilename) {
		try {
			byte[] bytes = Files.readAllBytes(Paths.get(sqlFilename));
			return new String(bytes, Charset.forName("UTF-8"));
		} catch (IOException ioe) {
			logger.error(ioe.getMessage(), ioe);
		}
		return "";
	}
}
