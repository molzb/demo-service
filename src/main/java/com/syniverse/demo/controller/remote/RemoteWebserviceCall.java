package com.syniverse.demo.controller.remote;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RestController
public class RemoteWebserviceCall extends BaseController {
	// private String urlTpl =
	// "http://localhost:8881/tms/v1/hpmnTariffs/%s/%s?usageType=C";
	@Setter
	@Value("${external.hpmnTariffsUrl}")
	private String hpmnTariffsUrlTpl;

	@Setter
	@Value("${external.userService.companyUsers}")
	private String userServiceCompanyUsers;

	RestTemplate tpl = new RestTemplate();
	RestTemplate tplSecure = new RestTemplate();

	@GetMapping(value = "hpmnTariffsForC/{hpmn}/{vpmn}", produces = APPLICATION_JSON_VALUE)
	public String getHpmnTariffsFromRemoteServer(@PathVariable String hpmn, @PathVariable String vpmn)
			throws URISyntaxException {
		URI url = new URI(String.format(hpmnTariffsUrlTpl, hpmn, vpmn));
		return tpl.getForObject(url, String.class);
	}

	@GetMapping("hpmnTariffObjectsForC/{hpmn}/{vpmn}")
	public List<Tariff> getTariffObjectsFromRemoteServer(@PathVariable String hpmn, @PathVariable String vpmn)
			throws URISyntaxException, JsonParseException, IOException {
		URI url = new URI(String.format(hpmnTariffsUrlTpl, hpmn, vpmn));
		return Arrays.asList(tpl.getForObject(url, Tariff[].class));
	}

	@GetMapping("hpmnTariffsEntityForC/{hpmn}/{vpmn}")
	public List<Tariff> getTariffsEntityFromRemoteServer(@PathVariable String hpmn, @PathVariable String vpmn)
			throws URISyntaxException, JsonParseException, IOException {
		URI url = new URI(String.format(hpmnTariffsUrlTpl, hpmn, vpmn));
		ResponseEntity<Tariff[]> entity = tpl.getForEntity(url, Tariff[].class);
		return List.of(entity.getBody());
	}

	@GetMapping("userDataFromSecuredServer/{pmn}")
	public List<UserData> getUserDataFromSecuredServer(@PathVariable String pmn) throws URISyntaxException {
		URI url = new URI(userServiceCompanyUsers);
		tplSecure = getRestTemplateWithBasicAuth("admin", "admin");
		return Arrays.asList(tplSecure.getForObject(url, UserData[].class));
	}
}

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
class Tariff {
	private String vplmnid;
	private String vpmn;
	private String vcountry;
	private String vcompanyName;
	private String tariffName;
}

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
class UserData {
	private String id;
	private String organization;
	private String email;
}