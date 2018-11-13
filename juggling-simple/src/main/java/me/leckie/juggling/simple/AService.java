package me.leckie.juggling.simple;

import me.leckie.juggling.facade.AInterface;
import me.leckie.juggling.facade.BInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author laixianbo
 * @version $Id: AService.java, v0.1 2018/11/13 14:59 laixianbo Exp $$
 */
@Service
public class AService implements AInterface {

  @Autowired
  private BInterface bInterface;

  @Override
  public String a(String a) {
    return String.format("%s from %s, %s", a, getClass().getName(), bInterface.b(a));
  }
}
