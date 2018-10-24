/**
 * BBD Service Inc All Rights Reserved @2018
 */
package me.leckie.juggling.jmx.mbean;

/**
 * @author laixianbo
 * @version $Id: HelloWorldMBean.java, v0.1 2018/10/24 14:40 laixianbo Exp $$
 */
public interface HelloWorldMBean {

  String getGreeting();

  void setGreeting(String greeting);

  void printGreeting();

}
