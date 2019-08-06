package me.lceckie.juggling.box.proxy;

/**
 * @author Leckie
 * @version AboutTryAndReturn.java, v0.1 2019-08-06 00:17
 */
public class AboutTryAndReturn {

  public AboutTryAndReturn(String hello) {
    super();
    this.message = hello;
  }

  private volatile String message;

  public synchronized String hello() {
    try {

      message = "at try";
      return message;
    } catch (Exception e) {
      message = "at catch.";
    } finally {
      message = "at finally";
      AboutTryAndReturn aboutTryAndReturn = new AboutTryAndReturn("");
      aboutTryAndReturn.message = "at finally";
      // return aboutTryAndReturn;
      System.out.println("in finally.");
      return message;
    }
    // message = "at last.";
    //return message;
  }

  public static void main(String[] args) {
    AboutTryAndReturn aboutTryAndReturn = new AboutTryAndReturn("");
    System.out.println(aboutTryAndReturn.hello());
  }

}
