package me.leckie.juggling.jmx.bean;

import me.leckie.juggling.jmx.mbean.HelloWorldMBean;

/**
 * @author Leckie
 * @version $Id: HelloWorld.java, v0.1 2018/10/24 14:42 Leckie Exp $$
 */
public class HelloWorld implements HelloWorldMBean {

  private String greeting;

  public HelloWorld() {
    this.greeting = "Hello, World!";
  }

  @Override
  public String getGreeting() {
    return greeting;
  }

  @Override
  public void setGreeting(String greeting) {
    this.greeting = greeting;
  }

  @Override
  public void printGreeting() {
    System.out.println(greeting);
  }
}
