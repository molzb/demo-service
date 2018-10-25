package com.syniverse.demo.controller.cache;

import java.util.Date;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@EnableCaching
@EnableScheduling
@RestController
public class Caching {
	@GetMapping("/irzoneTbl")
	@Cacheable("irzoneTbl")
	public String selectStarFromIrzoneTbl() throws InterruptedException {
		System.out.println("SELECT * FROM irzonetbl");
		Thread.sleep(10000);
		return "nix drin";
	}

	@GetMapping("/exchangeRate/{cc}")
	@Cacheable(value = "exchangeRate", condition = "#cc != 'EUR'")
	public String selectExchangeRate(@PathVariable String cc) {
		System.out.println("get Exchangerate for " + cc);
		char[] c = cc.toCharArray();
		String s = "";
		for (int i = 0; i < c.length; i++) {
			s += Character.getNumericValue(c[i]);
		}
		return s;
	}

	@CacheEvict(cacheNames= {"exchangeRate"}, allEntries = true)
	@Scheduled(cron="30 * * * * MON-FRI")
	public void refreshCurrencies() {
		System.out.println("Getting the freshest exchange rates at " + new Date());
	}
}
