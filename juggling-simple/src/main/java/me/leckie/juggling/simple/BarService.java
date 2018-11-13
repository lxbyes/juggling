package me.leckie.juggling.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author laixianbo
 * @version $Id: BarService.java, v0.1 2018/11/13 10:18 laixianbo Exp $$
 */
@Service
public class BarService {

  @Autowired
  private FooService fooService;

  public void say() {
    System.out.println(" -> I'm a instance of " + getClass().getName());
    fooService.say();
  }

}
