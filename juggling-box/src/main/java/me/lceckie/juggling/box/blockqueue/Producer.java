package me.lceckie.juggling.box.blockqueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Leckie
 * @version $Id: Producer.java, v0.1 2019/3/7 18:18 john Exp $$
 */
public class Producer implements Runnable {

  private BlockingQueue<String> queue;

  private final int maxRound;

  public Producer(BlockingQueue<String> queue, int maxRound) {
    this.queue = queue;
    this.maxRound = maxRound;
  }

  @Override
  public void run() {
    int round = 0;
    while (round < maxRound) {
      Random random = new Random();
      int nextInt = 50 + random.nextInt(500);
      try {
        TimeUnit.MILLISECONDS.sleep(nextInt);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      try {
        String item = "Item" + round;
        queue.put(item);
        System.out.println(Thread.currentThread().getName() + " put -> " + item);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      round++;
    }
  }
}
