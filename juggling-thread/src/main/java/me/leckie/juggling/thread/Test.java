package me.leckie.juggling.thread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author laixianbo
 * @version $Id: Test.java, v0.1 2019/2/25 10:37 john Exp $$
 */
public class Test {

  public static void main(String[] args) {
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS,
        new LinkedBlockingDeque<>(5));
    for (int i = 0; i < 13; i++) {
      threadPoolExecutor.execute(new LongTimeTask());
    }
  }

}
