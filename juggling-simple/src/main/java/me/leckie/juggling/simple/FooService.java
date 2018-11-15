package me.leckie.juggling.simple;

import me.leckie.juggling.facade.listener.PostConstructAndPreDestroyListener;
import org.springframework.stereotype.Service;

/**
 * @author Leckie
 * @version $Id: SimpleService.java, v0.1 2018/11/13 10:15 Leckie Exp $$
 */
@Service
public class FooService implements PostConstructAndPreDestroyListener {

  public void say() {
    System.out.println(" -> I'm a instance of " + getClass().getName());
  }

  public void println() {
    FooUtils.println("can load utils.");
  }

  public void bar() {
    FooUtils.bar();
  }
}
