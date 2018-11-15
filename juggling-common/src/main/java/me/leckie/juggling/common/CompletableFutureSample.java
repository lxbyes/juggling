package me.leckie.juggling.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author Leckie
 * @version $Id: CompletableFutureSample.java, v0.1 2018/10/30 14:46 Leckie Exp $$
 */
public class CompletableFutureSample {

  public static void main(String[] args) {

    List<Integer> integers = new ArrayList<>();

    CompletableFuture[] completableFutures = IntStream.range(0, 16).mapToObj(Integer::valueOf)
        .map(i -> CompletableFuture.supplyAsync(() -> calc(i)).thenApplyAsync(h -> {
          System.out.println("thenApply -> " + h + ", " + Thread.currentThread().getName());
          return h;
        }).whenComplete((s, e) -> {
          System.out.println("whenComplete -> " + s + ", " + e + ", " + Thread.currentThread().getName());
          integers.add(s);
        })).toArray(CompletableFuture[]::new);
    CompletableFuture.allOf(completableFutures).whenComplete((r, e) -> {
      System.out
          .println("whenComaaa: " + r + ", " + integers);
    }).join();
    Optional<Integer> resultOptional = integers.stream().reduce((sum, x) -> sum + x);
    System.out.println("result -> " + resultOptional.orElse(0));
  }

  public static Integer calc(Integer i) {
    try {
      if (i % 3 == 0) {
        TimeUnit.SECONDS.sleep(3);
      } else if (i % 5 == 0) {
        TimeUnit.SECONDS.sleep(5);
      } else {
        TimeUnit.SECONDS.sleep(1);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("Task " + Thread.currentThread().getName() + " i=" + i + " complete.");
    return i;
  }

}
