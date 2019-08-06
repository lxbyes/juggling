package me.leckie.juggling.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Leckie
 * @version LogAspect.java, v0.1 2019-08-06 22:57
 */
@Aspect
@Component
public class LogAspect {

  @Pointcut("execution(* me.leckie..*.*(..))")
  private void helloPoint() {
  }

  @Before("execution(* me.leckie..*.*(..)) && args(name)")
  public void beforeHello(String name) {
    System.out.println("before..." + name);
  }

  @After("LogAspect.helloPoint()")
  public void afterHello() {
    System.out.println("after...");
  }

  @AfterThrowing("LogAspect.helloPoint()")
  public void afterThrowing() {
    System.out.println("afterThrowing...");
  }

  @AfterReturning("LogAspect.helloPoint()")
  public void afterReturning() {
    System.out.println("afterReturning...");
  }

  @Around("LogAspect.helloPoint()")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    System.out.println(joinPoint.getSignature().getName() + "(" + joinPoint.getArgs()[0] + ")");
    return joinPoint.proceed();
  }
}
