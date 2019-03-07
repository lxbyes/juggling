package me.lceckie.juggling.box.blockqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Leckie
 * @version $Id: Main.java, v0.1 2019/3/7 18:31 john Exp $$
 */
public class Main {

  public static void main(String[] args) {
    BlockingQueue<String> queue = new LinkedBlockingQueue<>(5);
    Producer producer = new Producer(queue);
    Consumer consumer = new Consumer(queue);
    new Thread(producer).start();
    new Thread(consumer).start();
  }

}
