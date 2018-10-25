package com.syniverse.demo.controller.remote;

import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.Base64;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class BaseController {
	private static String getCredentialsAsBase64(String username, String password) {
		String authString = username + ":" + password;
		byte[] authStringAsByteArray = authString.getBytes(Charset.defaultCharset());
		return new String(Base64.getEncoder().encode(authStringAsByteArray), Charset.defaultCharset());
	}

	public static RestTemplate getRestTemplateWithBasicAuth(String username, String password) {
		String credentialsAsBase64 = getCredentialsAsBase64(username, password);
		return new RestTemplate(new SimpleClientHttpRequestFactory() {
			@Override
			protected void prepareConnection(HttpURLConnection connection, String httpMethod) {
				connection.setRequestProperty("Authorization", "Basic " + credentialsAsBase64);
			}
		});
	}
}
