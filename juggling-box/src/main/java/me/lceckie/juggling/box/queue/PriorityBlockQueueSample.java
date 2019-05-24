package me.lceckie.juggling.box.queue;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Leckie
 * @version $Id: PriorityBlockQueueSample.java, v0.1 2019/5/23 17:24 john Exp $$
 */
public class PriorityBlockQueueSample {

  public static void main(String[] args) {
    PriorityBlockingQueue<Passenger> priorityQueue = new PriorityBlockingQueue(3);
    Thread flightAttendant = new Thread(() -> {
      while (true) {
        try {
          Passenger passenger = priorityQueue.take();
          System.out.println("当前办理: " + passenger.getName() + ", " + passenger.getVipType());
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        // 模拟任务耗时
        try {
          TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    Thread passengerQueue = new Thread(() -> {
      Random random = new Random();
      for (int i = 0; i < 20; i++) {
        Passenger passenger = new Passenger();
        passenger.setName("Passenger_" + i);
        passenger.setVipType(VipType.typeOf(random.nextInt(102) % 3));
        System.out.println("乘客: " + passenger.getName() + ", " + passenger.getVipType() + "开始排队");
        priorityQueue.offer(passenger);
        try {
          TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    flightAttendant.start();
    passengerQueue.start();
  }

  enum VipType {

    DIAMOND, PLATINUM, GOLD;

    public static VipType typeOf(int ordinal) {
      for (VipType vipType : values()) {
        if (ordinal == vipType.ordinal()) {
          return vipType;
        }
      }
      return null;
    }
  }

  static class Passenger implements Comparable<Passenger> {

    private String name;

    private VipType vipType;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public VipType getVipType() {
      return vipType;
    }

    public void setVipType(VipType vipType) {
      this.vipType = vipType;
    }

    @Override
    public int compareTo(Passenger o) {
      return this.getVipType().ordinal() - o.getVipType().ordinal();
    }
  }

}
