package me.leckie.juggling.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leckie
 * @version $Id: TestForEffective.java, v0.1 2018/10/29 9:21 Leckie Exp $$
 */
public class TestForEffective {

  private static final int THRESHOLD = 1000_000;
  private static final List<String> DATA = new ArrayList<>();

  public TestForEffective() {
    for (int i = 0; i < THRESHOLD; i++) {
      DATA.add(String.valueOf(i));
    }
  }

  public void generalFor() {
    long startTime = System.nanoTime();
    int sum = 0;
    for (int i = 0; i < DATA.size(); i++) {
      int num = Integer.parseInt(DATA.get(i));
      if (num % 2 == 0) {
        sum += num;
      } else {
        sum -= num;
      }
    }
    long endTime = System.nanoTime();
    long mills = (endTime - startTime) / 1000_000;
    System.out.println(String.format("general for took %s mills. sum is %s.", mills, sum));
  }

  public void enhanceFor() {
    long startTime = System.nanoTime();
    int sum = 0;
    for (String s : DATA) {
      int i = Integer.parseInt(s);
      if (i % 2 == 0) {
        sum += i;
      } else {
        sum -= i;
      }
    }
    long endTime = System.nanoTime();
    long mills = (endTime - startTime) / 1000_000;
    System.out.println(String.format("enhance for took %s mills. sum is %s.", mills, sum));
  }

  public void forEachFor() {
    long startTime = System.nanoTime();
    int sum = 0;
    DATA.forEach(s -> {
      int i = Integer.parseInt(s);
      if (i % 2 == 0) {
        // i += i;
      } else {
        // i -= i;
      }
    });
    long endTime = System.nanoTime();
    long mills = (endTime - startTime) / 1000_000;
    System.out.println(String.format("forEach for took %s mills. sum is %s.", mills, sum));
  }

  public void streamFor() {
    long startTime = System.nanoTime();
    int sum = 0;
    DATA.stream().forEach(s -> {
      int i = Integer.parseInt(s);
      if (i % 2 == 0) {
        // i += i;
      } else {
        // i -= i;
      }
    });
    long endTime = System.nanoTime();
    long mills = (endTime - startTime) / 1000_000;
    System.out.println(String.format("stream for took %s mills. sum is %s.", mills, sum));
  }

  public void parallelStreamFor() {
    long startTime = System.nanoTime();
    int sum = 0;
    DATA.parallelStream().forEach(s -> {
      int i = Integer.parseInt(s);
      if (i % 2 == 0) {
        // i += i;
      } else {
        // i -= i;
      }
    });
    long endTime = System.nanoTime();
    long mills = (endTime - startTime) / 1000_000;
    System.out.println(String.format("parallelStream for took %s mills. sum is %s.", mills, sum));
  }

  public static void main(String[] args) throws IOException {
    TestForEffective effective = new TestForEffective();
    // effective.forEachFor();
    // effective.streamFor();
    effective.parallelStreamFor();
    effective.streamFor();
    effective.generalFor();
    effective.enhanceFor();
    // System.in.read();
  }

}
