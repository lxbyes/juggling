package me.lceckie.juggling.box.some;

import java.util.function.Predicate;

/**
 * @author Leckie
 * @version InnerClassTest.java, v0.1 2019/7/31 9:09
 */
public class InnerClassTest {

  private Predicate predicate = new NewPredicate();


  class NewPredicate implements Predicate {

    @Override
    public boolean test(Object o) {
      return false;
    }
  }

  public static void main(String[] args) {
    Predicate notNull = new Predicate() {
      @Override
      public boolean test(Object o) {
        return o == null ? false : true;
      }
    };
    System.out.println(notNull.test(null));
    new InnerClassTest().predicate.test(null);
  }
}
