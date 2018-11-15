package me.leckie.juggling.common;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Leckie
 * @version $Id: StreamSample.java, v0.1 2018/10/29 14:24 Leckie Exp $$
 */
public class StreamSample {

  private static void printLine() {
    System.out.println("------------------------------");
  }

  public static void main(String[] args) {
    DoubleStream.of(1.0, 2.3, 3.4, 4.5).filter((f) -> {
      System.out.println("filter -> " + f);
      return f > 3;
    }).map((f) -> {
      System.out.println("map -> " + f);
      return ++f;
    }).forEach(f -> System.out.println("forEach -> " + f));
    printLine();

    boolean anyMatch = Stream.of("a2", "b2", "c2").map(c -> {
      System.out.println("map -> " + c);
      return c.toUpperCase();
    }).anyMatch(c -> {
      System.out.println("anyMatch -> " + c);
      return c.startsWith("B");
    });
    System.out.println("Result: anyMatch -> " + anyMatch);
    System.out.format("test %s\n", "real");
    printLine();

    OptionalInt reduce = IntStream.range(1, 10).reduce((i, j) -> {
      System.out.println(String.format("i=%d, j=%d", i, j));
      return i + j;
    });
    System.out.format("sum of %d-%d is %d\n", 1, 10, reduce.orElse(-1));
    printLine();

    int sum10based = IntStream.range(1, 10).reduce(10, (i, j) -> {
      System.out.println(String.format("i=%d, j=%d", i, j));
      return i + j;
    });
    System.out.format("sum of %d-%d is %d\n", 1, 10, sum10based);
    printLine();

    int sum20based = IntStream.range(0, 1000).mapToObj(Integer::valueOf).parallel().reduce(0, (sum, i) -> {
      System.out.println(String.format("sum=%d, i=%d, thread=%s", sum, i, Thread.currentThread().getName()));
      return sum + i;
    }, (i, j) -> {
      System.out.println(String.format("i=%s, j=%s, thread=%s", i, j, Thread.currentThread().getName()));
      return i + j;
    });
    System.out.format("sum of 0-999 is %d\n", sum20based);
    printLine();

    Integer[] integers = Stream.of(1, 2, 3, 4).toArray(Integer[]::new);
    System.out.println(Arrays.asList(integers));
    printLine();
  }
}
