package com.durga.logger;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GlobalLogger {
	
	
	private static final Logger LOGGER=LoggerFactory.getLogger(GlobalLogger.class);
	@Pointcut("execution (* com.durga ..*(..))")
	public void pointcut() {
		
	}
	@Around("pointcut()")
	public Object aroundLog(ProceedingJoinPoint joinPoint) throws Throwable {
		LOGGER.info("start->>"+joinPoint.getSignature());
		Object obj=joinPoint.proceed();
		LOGGER.info("end->>"+joinPoint.getSignature());
		return obj;
	}
	
	@AfterThrowing(pointcut="pointcut()",throwing="error")
	public void afterThrowing(Joinpoint joinpoint,Exception error) {
		LOGGER.error(error.getMessage(),error);
	}
}
