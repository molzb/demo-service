package com.syniverse.demo.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

//@Component
public class BeanWithPostProcessor implements BeanPostProcessor {
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		String beanClass = bean.getClass().getSimpleName();
		System.out.println(getClass().getSimpleName() + " before init, " + beanClass + " name=" + beanName);
		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}

}
