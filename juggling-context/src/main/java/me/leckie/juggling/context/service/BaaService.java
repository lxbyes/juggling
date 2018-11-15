package me.leckie.juggling.context.service;

import me.leckie.juggling.facade.BInterface;
import me.leckie.juggling.facade.listener.PostConstructListener;
import me.leckie.juggling.facade.listener.PreDestroyListener;
import org.springframework.stereotype.Service;

/**
 * @author laixianbo
 * @version $Id: BaaService.java, v0.1 2018/11/14 16:11 laixianbo Exp $$
 */
@Service
public class BaaService implements BInterface, PostConstructListener, PreDestroyListener {

  @Override
  public String b(String b) {
    return b + " in " + getClass().getName();
  }
}