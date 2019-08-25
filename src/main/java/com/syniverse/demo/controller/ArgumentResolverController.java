package com.syniverse.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ArgumentResolverController {
	@Autowired
	HttpServletRequest req;

	@GetMapping("domain")
	public @ResponseBody String getDomain() {
		return req.getServerName();
	}

	@GetMapping("domainWithResolver")
	public @ResponseBody String getDomainWithResolver(String domain) {
		return domain;
	}
}
