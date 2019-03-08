package com.syniverse.demo.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AopComponent {
	Logger logger = LoggerFactory.getLogger(AopComponent.class);

	// Test with: http://localhost:8888/hpmnTariffsForC/DEUD1/DEUD2
	@Before("com.syniverse.demo.aop.PointcutDefinitions.someExternallyReferencedPointcut()")
	public void beforeExternallyReferencedPointcut() {
		logger.info("before ExternallyReferencedPointcut ...");
	}
	
	@Before("execution(* com.syniverse.demo.controller.*.*(..))")
	public void beforeController() {
		System.out.println("before any Controller has been called");
	}

	// Test with: http://localhost:8888/  or  http://localhost:8888/date  or  .../hello/Bernhard
	@After("execution(* com.syniverse.demo.controller.HelloController.*(..))")
	public void afterHelloController() {
		System.out.println("after HelloController.* has been called");
	}

	// Test with: http://localhost:8888/raex  or  .../downloadPdf
	@AfterReturning(value = "execution(* com.syniverse.demo.controller.FileController.*(..))", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		String resultAsStr = result.toString();
		if (resultAsStr.length() > 50)
			resultAsStr = "\n" + resultAsStr.substring(0, 49) + "...";
		logger.info("{} returned with (shortended) value {}", joinPoint, resultAsStr);
	}

	// Test with: http://localhost:8888/sometimesThisWorks
	@AfterThrowing(value = "execution(* com.syniverse.demo.controller.HelloController.dateOrDie(..))", throwing = "throwing")
	public void afterThrowing(JoinPoint joinPoint, Object throwing) {
		logger.info("throwing=" + throwing);
		logger.info("{} threw an exception.\nThrows = {}", joinPoint, throwing.toString());
	}

	// Test with: http://localhost:8888/countries or .../countryNames or .../country/ARG
	@Around(value = "execution(* com.syniverse.demo.controller.mdm.CountryController.*(..))")
	public Object aroundFileController(ProceedingJoinPoint joinPoint) throws Throwable {
		long s1 = System.currentTimeMillis();
		logger.info("around {} with args {}", joinPoint, Arrays.deepToString(joinPoint.getArgs()));
		Object returnValue = joinPoint.proceed();
		logger.info("Duration {} ms", System.currentTimeMillis() - s1);
		return returnValue;
	}
}
