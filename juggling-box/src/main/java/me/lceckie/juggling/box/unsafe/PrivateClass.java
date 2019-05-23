package me.lceckie.juggling.box.unsafe;

/**
 * @author laixianbo
 * @version $Id: PrivateClass.java, v0.1 2019/5/21 19:27 john Exp $$
 */
public class PrivateClass {

  private String message;

  private PrivateClass() {
    message = "Hello, constructor.";
    System.out.println(PrivateClass.class.getName() + " constructed.");
  }

  public void say() {
    System.out.println(message);
  }

}
