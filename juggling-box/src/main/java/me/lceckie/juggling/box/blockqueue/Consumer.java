package me.lceckie.juggling.box.blockqueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Leckie
 * @version $Id: Consumer.java, v0.1 2019/3/7 18:18 john Exp $$
 */
public class Consumer implements Runnable {

  private BlockingQueue<String> queue;

  public Consumer(BlockingQueue<String> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    int round = 0;
    while (round < 200) {
      Random random = new Random();
      int nextInt = 200 + random.nextInt(1000);
      try {
        TimeUnit.MILLISECONDS.sleep(nextInt);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      try {
        String item = queue.take();
        System.out.println(Thread.currentThread().getName() + " take -> " + item);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      round++;
    }
  }
}
