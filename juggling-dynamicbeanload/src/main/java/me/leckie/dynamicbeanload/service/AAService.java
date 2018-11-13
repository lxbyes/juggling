package me.leckie.dynamicbeanload.service;

import me.leckie.juggling.facade.AInterface;
import org.springframework.stereotype.Service;

/**
 * @author laixianbo
 * @version $Id: AAService.java, v0.1 2018/11/13 15:35 laixianbo Exp $$
 */
@Service
public class AAService implements AInterface {

  @Override
  public String a(String a) {
    return AAService.class.getName() + ":" + a;
  }
}
