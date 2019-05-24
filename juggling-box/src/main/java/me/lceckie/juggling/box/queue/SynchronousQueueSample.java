package me.lceckie.juggling.box.queue;

import java.util.concurrent.SynchronousQueue;

/**
 * @author Leckie
 * @version $Id: SynchronousQueueSample.java, v0.1 2019/5/24 15:17 john Exp $$
 */
public class SynchronousQueueSample {

  public static void main(String[] args) throws InterruptedException {
    SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();
    new Thread(() -> {
      try {
        print("start offer");
        synchronousQueue.put(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
    new Thread(() -> {
      try {
        print("start take");
        print(synchronousQueue.take());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
  }

  private static void print(Object message) {
    System.out.println(Thread.currentThread().getName() + " -> " + message);
  }

}
