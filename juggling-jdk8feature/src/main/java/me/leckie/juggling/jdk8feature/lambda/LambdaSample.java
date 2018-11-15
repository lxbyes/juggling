package me.leckie.juggling.jdk8feature.lambda;

/**
 * @author Leckie
 * @version $Id: LambdaSample.java, v0.1 2018/10/24 17:38 Leckie Exp $$
 */
public class LambdaSample {

  public static void main(String[] args) {
    new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
  }

}
