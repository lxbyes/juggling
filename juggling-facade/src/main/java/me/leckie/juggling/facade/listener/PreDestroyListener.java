package me.leckie.juggling.facade.listener;

import javax.annotation.PreDestroy;

/**
 * @author Leckie
 * @version $Id: PreDestroyListener.java, v0.1 2018/11/14 16:17 Leckie Exp $$
 */
public interface PreDestroyListener {

  @PreDestroy
  default void preDestroy() {
    System.out.println(getClass().getName() + " - will destroy.");
  }

}
