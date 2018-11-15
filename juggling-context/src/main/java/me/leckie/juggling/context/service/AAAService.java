package me.leckie.juggling.context.service;

import me.leckie.juggling.facade.AInterface;
import me.leckie.juggling.facade.listener.PostConstructAndPreDestroyListener;

/**
 * @author laixianbo
 * @version $Id: AAAService.java, v0.1 2018/11/15 15:55 laixianbo Exp $$
 */
public class AAAService implements AInterface, PostConstructAndPreDestroyListener {

  @Override
  public String a(String a) {
    return a + " from " + getClass().getName();
  }
}
