package me.leckie.juggling.simple;

import me.leckie.juggling.facade.listener.PostConstructAndPreDestroyListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Leckie
 * @version $Id: BarService.java, v0.1 2018/11/13 10:18 Leckie Exp $$
 */
@Service
public class BarService implements PostConstructAndPreDestroyListener {

  @Autowired
  private FooService fooService;

  public void say() {
    System.out.println(" -> I'm a instance of " + getClass().getName());
    fooService.say();
  }

}
