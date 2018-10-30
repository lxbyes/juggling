/**
 * BBD Service Inc All Rights Reserved @2018
 */
package me.leckie.juggling.common;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author laixianbo
 * @version $Id: ModernNPESafeSample.java, v0.1 2018/10/30 9:41 laixianbo Exp $$
 */
public class ModernNPESafeSample {

  static class Outer {

    Nested nested;

    public Nested getNested() {
      return nested;
    }
  }

  static class Nested {

    Inner inner;

    public Inner getInner() {
      return inner;
    }
  }

  static class Inner {

    String foo;

    public String getFoo() {
      return foo;
    }
  }

  public static void main(String[] args) {
    Outer outer = new Outer();
    // outer.nested = new Nested();
    // outer.nested.inner = new Inner();
    // outer.nested.inner.foo = "foo";
    Optional.of(outer).map(Outer::getNested).map(Nested::getInner).map(Inner::getFoo).ifPresent(System.out::println);
    resolve(() -> outer.getNested().getInner().getFoo()).ifPresent(System.out::println);
  }

  public static <T> Optional<T> resolve(Supplier<T> supplier) {
    try {
      T result = supplier.get();
      return Optional.ofNullable(result);
    } catch (NullPointerException e) {
      return Optional.empty();
    }
  }

}
