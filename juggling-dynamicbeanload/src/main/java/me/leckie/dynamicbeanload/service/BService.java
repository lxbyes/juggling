package me.leckie.dynamicbeanload.service;

import me.leckie.juggling.facade.BInterface;
import org.springframework.stereotype.Service;

/**
 * @author Leckie
 * @version $Id: BService.java, v0.1 2018/11/13 14:56 Leckie Exp $$
 */
@Service
public class BService implements BInterface {

  @Override
  public String b(String b) {
    return String.format("%s from %s", b, getClass().getName());
  }
}
