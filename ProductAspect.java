package com.hsbc.retail.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ProductAspect {

	//Logging
	Logger logger=LoggerFactory.getLogger(this.getClass());
	@Pointcut("execution(* *.*(..))")
	public void allMethods()
	{
		//System.out.println("methods invoked.....");
	}
	
	@Pointcut("execution(void com.hsbc.reatil.models.*.set*(*))")
	public void allSetterMethods()
	{
		
	}
	
	//join point
		@Before("allMethods()")
		public void logBefore(JoinPoint joinPoint)
		{
			logger.info("Entering...."+joinPoint.getSignature().getDeclaringTypeName());
			
		}
		
		@After("args(name)")
		public void logStringArguments(String name){
			logger.info("Running After Advice. String argument passed="+name);
		}
		
		@AfterThrowing("within(com.hsbc.retail.models.Product)")
		public void logExceptions(JoinPoint joinPoint){
			logger.info("Exception thrown in Product Method="+joinPoint.toString());
		}
		
		@AfterReturning(pointcut="execution(* getName())", returning="returnString")
		public void getNameReturningAdvice(String returnString){
			logger.info("getNameReturningAdvice executed. Returned String="+returnString);
		}


}
