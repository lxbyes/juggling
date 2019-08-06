package me.leckie.juggling.aop.service;

import org.springframework.stereotype.Service;

/**
 * @author Leckie
 * @version HelloService.java, v0.1 2019-08-06 22:52
 */
@Service
public class HelloService {

  public void hello(String name) {
    System.out.println("Hello, " + name);
  }

}
