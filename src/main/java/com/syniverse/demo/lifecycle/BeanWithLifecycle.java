package com.syniverse.demo.lifecycle;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class BeanWithLifecycle implements InitializingBean, DisposableBean, BeanNameAware {

	public BeanWithLifecycle() {
		System.out.println(getClass().getSimpleName() + " has been constructed");
	}

	@Override
	public void setBeanName(String name) { // BeanNameAware
		System.out.println(getClass().getSimpleName() + " is in setBeanName " + name);
	}

	@PostConstruct
	public void postConstruct() {
		System.out.println(getClass().getSimpleName() + " is in PostConstruct");
	}

	@Override
	public void afterPropertiesSet() throws Exception { // InitializingBean
		System.out.println(getClass().getSimpleName() + " has been initialized ");
	}

	@Override
	public void destroy() throws Exception { // DisposableBean
		// Im Log nach "Shutting down ExecutorService ..."
		System.out.println(getClass().getSimpleName() + " will be destroyed now");
	}

}
