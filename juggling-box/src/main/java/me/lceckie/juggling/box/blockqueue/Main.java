package me.lceckie.juggling.box.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * @author Leckie
 * @version $Id: Main.java, v0.1 2019/3/7 18:31 john Exp $$
 */
public class Main {

  public static void main(String[] args) {
    BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    new LinkedTransferQueue<>();
    Producer producer = new Producer(queue, 30);
    Consumer consumer = new Consumer(queue, 30);
    new Thread(producer, "producer").start();
    new Thread(consumer, "consumer").start();
  }

}
