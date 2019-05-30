package me.lceckie.juggling.box.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Leckie
 * @version 1: LockSample.java, v0.1 2019/5/29 14:41 john Exp $$
 */
public class LockSample {

  class Task implements Runnable {

    public Thread other;

    private Lock lock;

    private String name;

    public Task(Lock lock, String name) {
      this.lock = lock;
      this.name = name;
    }

    public void setOther(Thread other) {
      this.other = other;
    }

    @Override
    public void run() {
      lock.lock();
      System.out.println("Task " + name + " get the lock.");
      other.interrupt();
      lock.unlock();
    }
  }

  class TaskInterrupted implements Runnable {

    public Thread other;

    private Lock lock;

    private String name;

    public TaskInterrupted(Lock lock, String name) {
      this.lock = lock;
      this.name = name;
    }

    public void setOther(Thread other) {
      this.other = other;
    }

    @Override
    public void run() {
      try {
        lock.lockInterruptibly();
        System.out.println("Task " + name + " get the lock.");
        other.interrupt();
        lock.unlock();
      } catch (InterruptedException e) {
        System.out.println("Task " + name + " is interrupted.");
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    Lock lock = new ReentrantLock();
    LockSample lockSample = new LockSample();
    Task task1 = lockSample.new Task(lock, "task1");
    Task task2 = lockSample.new Task(lock, "task2");
    Thread thread1 = new Thread(task1);
    Thread thread2 = new Thread(task2);
    task1.setOther(thread2);
    task2.setOther(thread1);
    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();

    System.out.println("------------------------");

    TaskInterrupted taskInterrupted1 = lockSample.new TaskInterrupted(lock, "TaskInterrupted1");
    TaskInterrupted taskInterrupted2 = lockSample.new TaskInterrupted(lock, "TaskInterrupted2");
    Thread thread3 = new Thread(taskInterrupted1);
    Thread thread4 = new Thread(taskInterrupted2);
    taskInterrupted1.setOther(thread4);
    taskInterrupted2.setOther(thread3);
    thread3.start();
    thread4.start();
  }

}
