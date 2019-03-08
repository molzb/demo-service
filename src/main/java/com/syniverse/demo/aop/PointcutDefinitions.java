package com.syniverse.demo.aop;

import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PointcutDefinitions {
	Logger logger = LoggerFactory.getLogger(PointcutDefinitions.class);
	
	// Test with: http://localhost:8888/hpmnTariffsForC/DEUD1/DEUD2
//	@Pointcut("execution(* getHpmnTariffsFromRemoteServer(..))")	// ok
//	@Pointcut("execution(* getHpmnTariffsFromRemoteServer(..)) && args(String,..)")	// ok, will be called
	@Pointcut("execution(* getHpmnTariffsFromRemoteServer(..)) && args(Integer,..)")// ok, won't be called
	public void someExternallyReferencedPointcut() {
		logger.info("@Pointcut referenced");	// will not be executed. Why?
	}
}
