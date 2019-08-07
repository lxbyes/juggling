package me.lceckie.juggling.box.some;

import java.time.LocalDateTime;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Leckie
 * @version ThreadTest.java, v0.1 2019/8/3 10:21
 */
public class ThreadTest {

  int i = 1;

  /*
  public static void main(String[] args) {
    ThreadTest a = new ThreadTest();
    Runnable r = () -> {
      synchronized (a) {
        while (a.i <= 100) {
          try {
            System.out.println(Thread.currentThread().getName() + ": " + a.i++);
            a.notify();
            a.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        a.notify();
      }
    };
    new Thread(r).start();
    new Thread(r).start();
  }*/

  public static void main(String[] args) throws InterruptedException {
    LocalDateTime localDateTime = LocalDateTime.now();
    System.out.println(localDateTime.getSecond() & 0X7);

    SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>(true);
    Runnable r = () -> {
      while (true) {
        try {
          Integer i = synchronousQueue.take();
          if (i > 100) {
            synchronousQueue.offer(i + 1, 1L, TimeUnit.SECONDS);
            break;
          }
          System.out.println(Thread.currentThread().getName() + ": " + i);
          synchronousQueue.put(i + 1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    new Thread(r).start();
    new Thread(r).start();
    synchronousQueue.put(1);
  }
}
