package me.leckie.juggling.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author laixianbo
 * @version $Id: LongTimeTask.java, v0.1 2019/2/25 9:40 john Exp $$
 */
public class LongTimeTask implements Runnable {

  @Override
  public void run() {
    int count = 0;
    while (count < 10) {
      try {
        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + " is running. count: " + count);
        count++;
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
