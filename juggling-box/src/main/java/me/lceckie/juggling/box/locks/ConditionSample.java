package me.lceckie.juggling.box.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author laixianbo
 * @version 2: ConditionSample.java, v0.1 2019/5/31 9:45 john Exp $$
 */
public class ConditionSample {

  private Lock lock = new ReentrantLock();

  private Condition putCondition = lock.newCondition();

  private Condition takeCondition = lock.newCondition();

  private final static int SIZE = 10;

  private CountDownLatch countDownLatch = new CountDownLatch(1);

  private List<String> stack = new ArrayList<>(SIZE);

  public void put(String str) {
    lock.lock();
    try {
      while (stack.size() == SIZE) {
        putCondition.await();
      }
      stack.add(str);
      System.out.println(Thread.currentThread().getName() + " -> put: " + str);
      takeCondition.signal();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public String take() {
    lock.lock();
    try {
      if (stack.size() == 0) {
        takeCondition.await();
      }
      String str = stack.remove(stack.size() - 1);
      System.out.println(Thread.currentThread().getName() + " -> take: " + str);
      putCondition.signal();
      return str;
    } catch (InterruptedException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    } finally {
      lock.unlock();
    }
  }

  class PutTask implements Runnable {

    private ConditionSample stack;

    public PutTask(ConditionSample stack) {
      this.stack = stack;
    }

    @Override
    public void run() {
      try {
        stack.countDownLatch.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      stack.put(String.valueOf(UUID.randomUUID()));
    }
  }

  class TakeTask implements Runnable {

    private ConditionSample stack;

    public TakeTask(ConditionSample stack) {
      this.stack = stack;
    }

    @Override
    public void run() {
      try {
        stack.countDownLatch.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      stack.take();
    }
  }

  public static void main(String[] args) {
    ConditionSample conditionSample = new ConditionSample();
    for (int count = 0; count < 100; count++) {
      Runnable task = count % 2 == 0 ? conditionSample.new PutTask(conditionSample)
          : conditionSample.new TakeTask(conditionSample);
      new Thread(task).start();
    }
    conditionSample.countDownLatch.countDown();
  }

}
