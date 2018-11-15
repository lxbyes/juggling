package me.leckie.juggling.context.service;

import me.leckie.juggling.facade.AInterface;
import me.leckie.juggling.facade.BInterface;
import me.leckie.juggling.facade.listener.PostConstructAndPreDestroyListener;
import org.springframework.stereotype.Component;

/**
 * @author laixianbo
 * @version $Id: ABService.java, v0.1 2018/11/15 14:15 laixianbo Exp $$
 */
@Component
public class ABService implements AInterface, BInterface, PostConstructAndPreDestroyListener {

  @Override
  public String a(String a) {
    return a;
  }

  @Override
  public String b(String b) {
    return b;
  }
}
