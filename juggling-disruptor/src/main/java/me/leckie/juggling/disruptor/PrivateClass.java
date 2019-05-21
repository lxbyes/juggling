package me.leckie.juggling.disruptor;

/**
 * @author laixianbo
 * @version $Id: PrivateClass.java, v0.1 2019/5/21 19:27 john Exp $$
 */
public class PrivateClass {

  private String message;

  private PrivateClass() {
    message = PrivateClass.class.getName() + " constructed.";
    System.out.println(PrivateClass.class.getName() + " constructed.");
  }

  public void say() {
    System.out.println(message);
  }

}
