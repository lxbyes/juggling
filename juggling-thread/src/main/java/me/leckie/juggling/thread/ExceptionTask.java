package me.leckie.juggling.thread;

/**
 * @author Leckie
 * @version $Id: ExceptionTask.java, v0.1 2018/12/18 18:08 Leckie Exp $$
 */
public class ExceptionTask implements Runnable {

  @Override
  public void run() {
    throw new RuntimeException("task exception");
  }
}
