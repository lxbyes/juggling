package me.leckie.juggling.facade.listener;

import javax.annotation.PreDestroy;

/**
 * @author laixianbo
 * @version $Id: PreDestroyListener.java, v0.1 2018/11/14 16:17 laixianbo Exp $$
 */
public interface PreDestroyListener {

  @PreDestroy
  default void preDestroy() {
    System.out.println(getClass().getName() + " - will destroy.");
  }

}
