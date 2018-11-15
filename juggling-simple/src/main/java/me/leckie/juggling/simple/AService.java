package me.leckie.juggling.simple;

import me.leckie.juggling.facade.AInterface;
import me.leckie.juggling.facade.BInterface;
import me.leckie.juggling.facade.listener.PostConstructAndPreDestroyListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author Leckie
 * @version $Id: AService.java, v0.1 2018/11/13 14:59 Leckie Exp $$
 */
@Service
@Order(1)
public class AService implements AInterface, PostConstructAndPreDestroyListener {

  @Autowired
  private BInterface bInterface;

  @Override
  public String a(String a) {
    return String.format("%s from %s, %s", a, getClass().getName(), bInterface.b(a));
  }
}
