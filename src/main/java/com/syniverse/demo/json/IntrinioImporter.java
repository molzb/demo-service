package com.syniverse.demo.json;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@RestController
/**
 * Demonstrates the mapping of a complex JSON endpoint to Java via Jackson 
 */
public class IntrinioImporter {
	@GetMapping("/import/US_Securities")
	public SecuritiesFeed readUSCOMP() {
		String url = "http://localhost:8888/securitiesUSCOMP.json";
		RestTemplate tpl = new RestTemplate();
		SecuritiesFeed securities = tpl.getForObject(url, SecuritiesFeed.class);
		return securities;
	}
}

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
class SecuritiesFeed {
	List<Securities> securities;
	StockExchange stockExchange;
}

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
class Securities {
	String id;
	@JsonAlias("company_id")
	String companyId;
	@JsonAlias("company_exchange_id")
	String stockExchangeId;
	String name;
	String code;
	String currency;
	String ticker;
	@JsonAlias("composite_ticker")
	String composite_ticker;
}

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
@JsonRootName("stock_exchange")
class StockExchange {
	String id;
	String name;
	String mic;
	String acronym;
	String country;
}