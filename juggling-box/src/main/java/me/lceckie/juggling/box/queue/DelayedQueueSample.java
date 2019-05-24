package me.lceckie.juggling.box.queue;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.DelayQueue;

/**
 * @author Leckie
 * @version $Id: DelayedQueueSample.java, v0.1 2019/5/24 10:40 john Exp $$
 */
public class DelayedQueueSample {

  public static void main(String[] args) {

    DelayQueue<TimingMessage> queue = new DelayQueue<>();

    Thread producer = new Thread(() -> {
      Random random = new Random();
      for (int i = 0; i < 20; i++) {
        TimingMessage timingMessage = new TimingMessage("message " + i, random.nextInt(3000));
        queue.offer(timingMessage);
      }
    });

    Thread consumer = new Thread(() -> {
      while (true) {
        try {
          TimingMessage timingMessage = queue.take();
          System.out.println(
              "Execute timingMessage " + timingMessage.getMessage() + ", delayed=" + LocalDateTime.ofInstant(Instant
                  .ofEpochMilli(timingMessage.getExecuteTime()), ZoneId.systemDefault()).format(
                  DateTimeFormatter.ISO_TIME));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    producer.start();
    consumer.start();
  }

}
