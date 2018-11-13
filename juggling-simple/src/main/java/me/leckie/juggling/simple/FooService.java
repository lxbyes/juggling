package me.leckie.juggling.simple;

import org.springframework.stereotype.Service;

/**
 * @author laixianbo
 * @version $Id: SimpleService.java, v0.1 2018/11/13 10:15 laixianbo Exp $$
 */
@Service
public class FooService {

  public void say() {
    System.out.println(" -> I'm a instance of " + getClass().getName());
  }

}
