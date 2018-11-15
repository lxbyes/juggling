package me.leckie.juggling.context.service;

import me.leckie.juggling.facade.AInterface;
import me.leckie.juggling.facade.listener.PostConstructAndPreDestroyListener;
import org.springframework.core.annotation.Order;

/**
 * @author Leckie
 * @version $Id: AAAService.java, v0.1 2018/11/15 15:55 Leckie Exp $$
 */
@Order(3)
public class AAAService implements AInterface, PostConstructAndPreDestroyListener {

  @Override
  public String a(String a) {
    return a + " from " + getClass().getName();
  }
}
