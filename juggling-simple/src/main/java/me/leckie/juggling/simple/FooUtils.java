package me.leckie.juggling.simple;

/**
 * @author laixianbo
 * @version $Id: FooUtils.java, v0.1 2018/11/15 18:27 laixianbo Exp $$
 */
public abstract class FooUtils {

  public static final void println(String str) {
    System.out.println(str);
  }

  public static final void bar() {
    new Bar().say();
  }

}
