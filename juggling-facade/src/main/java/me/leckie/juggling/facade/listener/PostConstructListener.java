package me.leckie.juggling.facade.listener;

import javax.annotation.PostConstruct;

/**
 * @author laixianbo
 * @version $Id: PostConstructListener.java, v0.1 2018/11/14 15:51 laixianbo Exp $$
 */
public interface PostConstructListener {

  @PostConstruct
  default void onPostConstruct() {
    System.out.println(getClass().getName() + " - has constructed.");
  }

}
