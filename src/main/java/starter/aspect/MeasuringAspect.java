package starter.aspect;

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
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MeasuringAspect {
	
	Logger log = LoggerFactory.getLogger(MeasuringAspect.class);
	
	@Around("execution(* starter.service.*Service.*(..))")
	public Object measuringMethodRunningTime(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		try {
			return joinPoint.proceed();
		} finally {
			long runningTime = System.currentTimeMillis() - startTime;
			String targetMethodName = joinPoint.getSignature().getName();
			log.info(targetMethodName + " running time : " + runningTime);
		}
	}
	
	@Before("execution(* starter.service.*Service.*(..))")
	public void measuringBefore(JoinPoint joinPoint) {
	}
	
	@After("execution(* starter.service.*Service.*(..))")
	public void measuringAfter(JoinPoint joinPoint) {
	}
	
	@AfterReturning(pointcut="execution(* starter.service.*Service.*(..))", returning="result")
	public void measuringAfterReturning(Object result) throws Throwable {
	}
	
	@AfterThrowing(pointcut="execution(* starter.service.*Service.*(..))", throwing="ex")
	public void measuringAfterThrowing(Exception ex) throws Throwable {
	}
}