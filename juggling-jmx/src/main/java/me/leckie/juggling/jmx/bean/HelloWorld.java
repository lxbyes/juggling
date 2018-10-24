/**
 * BBD Service Inc All Rights Reserved @2018
 */
package me.leckie.juggling.jmx.bean;

import me.leckie.juggling.jmx.mbean.HelloWorldMBean;

/**
 * @author laixianbo
 * @version $Id: HelloWorld.java, v0.1 2018/10/24 14:42 laixianbo Exp $$
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
