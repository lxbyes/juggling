package me.lceckie.juggling.box.some;

import java.util.function.Predicate;

/**
 * @author Leckie
 * @version LamdbaTest.java, v0.1 2019/7/31 9:09
 */
public class LamdbaTest {

  public static void main(String[] args) {
    Predicate notNull = o -> o == null ? false : true;
    System.out.println(notNull.test(null));
  }
}
