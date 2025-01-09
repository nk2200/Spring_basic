package com.example.myapp.aop;

import java.lang.reflect.Modifier;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class HelloLog {
	
	@Pointcut("execution(* com.example.myapp.aop.HelloService.sayHello(..))")
	private void helloPointcut() {}
	
	@Before("helloPointcut()")
	public void log(JoinPoint joinPoint) {
		System.out.println(">>>LOG<<<: "+new java.util.Date());
		Signature s = joinPoint.getSignature();
		String methodName = s.getName();
		System.out.println(">>>BEFORE LOG<<< : 핵심코드 메서드명 - "+
		methodName);
		System.out.println("메서드가 선언된곳: "+s.getDeclaringTypeName());
		System.out.println("제한자: "+s.getModifiers());
		System.out.println("modifier: "+Modifier.toString(s.getModifiers()));
	}
	
	@After("helloPointcut()")
	public void afterLog(JoinPoint joinPoint) {
		Signature s = joinPoint.getSignature();
		String methodName = s.toShortString();
		System.out.println(">>>AFTER LOG<<<: 핵심코드 메서드명 - "+methodName);
		
	}
	@AfterReturning(value="helloPointcut()", returning="resultObj")
	public void resultLog(JoinPoint joinPoint, String resultObj) {
		Signature s = joinPoint.getSignature();
		String methodName = s.toShortString();
		System.out.println(">>>RESULT LOG<<<: 핵심코드 메서드명 - "+methodName);
		System.out.println("핵심코드의 반환 값:"+resultObj);
		
	}
	
	
}
