package com.syniverse.demo.controller.remote;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.web.client.MockRestServiceServer;

import com.fasterxml.jackson.core.JsonParseException;

public class RemoteWebserviceCallTest {
	private MockRestServiceServer mockServer;
	private MockRestServiceServer mockServerSecure;

	private RemoteWebserviceCall wsCall = new RemoteWebserviceCall();

	private String pmn = "06174";
	private String tariffJsonUrl = "http://localhost:9999/com/syniverse/demo/controller/remote/FRA.json";
	private String userJsonUrl = "http://localhost:9999/com/syniverse/demo/controller/remote/users.json";
	private byte[] tariffJsonContent;
	private byte[] userJsonContent;

	@Before
	public void setUp() throws IOException {
		mockServer = MockRestServiceServer.createServer(wsCall.tpl);
		wsCall.setHpmnTariffsUrlTpl(tariffJsonUrl);
		InputStream is = getClass().getResourceAsStream("/com/syniverse/demo/controller/remote/FRA.json");
		tariffJsonContent = is.readAllBytes();
		is.close();

		mockServerSecure = MockRestServiceServer.createServer(wsCall.tplSecure);
		wsCall.setUserServiceCompanyUsers(userJsonUrl);
		InputStream is2 = getClass().getResourceAsStream("/com/syniverse/demo/controller/remote/users.json");
		userJsonContent = is2.readAllBytes();
		is2.close();
	}

	@Test
	public void testGetTariffObjectsFromRemoteServer() throws URISyntaxException, JsonParseException, IOException {
		mockServer.expect(requestTo(tariffJsonUrl)).andExpect(method(GET))
				.andRespond(withSuccess(tariffJsonContent, APPLICATION_JSON_UTF8));

		List<Tariff> tariffObjects = wsCall.getTariffObjectsFromRemoteServer("h", "v");
		System.out.println(tariffObjects);
		Tariff firstTariff = tariffObjects.get(0);
		assertTrue("Calling the WS of a remote server should return some Tariff objects",
				tariffObjects != null && !tariffObjects.isEmpty());
		assertEquals("There should be 44 Tariff objects. ", 44, tariffObjects.size());

		assertTrue("The first tariff should have vplmnid=20810 and country=Mock France -> " + firstTariff,
				firstTariff.getVplmnid().equals("20810") && firstTariff.getVcountry().equals("Mock France"));
	}

	@Test
	public void testGetTariffsEntityFromRemoteServer() throws JsonParseException, URISyntaxException, IOException {
		mockServer.expect(requestTo(tariffJsonUrl)).andExpect(method(GET))
				.andRespond(withSuccess(tariffJsonContent, APPLICATION_JSON_UTF8));

		List<Tariff> tariffObjects = wsCall.getTariffsEntityFromRemoteServer("h", "v");
		System.out.println(tariffObjects);
		assertTrue("Calling the WS of a remote server should return some Tariff objects",
				tariffObjects != null && !tariffObjects.isEmpty());
		assertEquals("There should be 44 Tariff objects. ", 44, tariffObjects.size());
	}

	@Ignore("TODO Find out, how to mock secure RestTemplate with BasicAuth")
	public void testGetUserDataFromSecuredServer() throws URISyntaxException {
		mockServerSecure.expect(requestTo(userJsonUrl)).andExpect(method(GET))
				.andRespond(withSuccess(userJsonContent, APPLICATION_JSON_UTF8));

		List<UserData> userData = wsCall.getUserDataFromSecuredServer(pmn);
		System.out.println(userData);
		assertTrue("Calling the WS of the user-service should return some objects", userData != null && !userData.isEmpty());
	}
}
