package me.leckie.juggling.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Leckie
 * @version $Id: Main.java, v0.1 2018/12/18 18:11 Leckie Exp $$
 */
public class Main {

  public static void main(String[] args) {
    Thread thread = new Thread(new ExceptionTask());
    try {
      thread.start();
    } catch (Throwable e) {
      // e.printStackTrace();
    }

    System.out.println("----------------------");

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    try {
      executorService.execute(new ExceptionTask());
    } catch (Throwable e) {
    }

  }

}
