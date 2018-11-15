package me.leckie.juggling.jmx.mbean;

/**
 * @author Leckie
 * @version $Id: HelloWorldMBean.java, v0.1 2018/10/24 14:40 Leckie Exp $$
 */
public interface HelloWorldMBean {

  String getGreeting();

  void setGreeting(String greeting);

  void printGreeting();

}
