package com.syniverse.demo.scope;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class ScopedBean {
	public ScopedBean() {
		System.out.println("ScopedBean instantiated at " + new Date());
	}
}
