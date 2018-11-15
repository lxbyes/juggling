package me.leckie.dynamicbeanload.service;

import javax.annotation.PostConstruct;
import me.leckie.dynamicbeanload.web.controller.BeanController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Leckie
 * @version $Id: ABC.java, v0.1 2018/11/14 10:53 Leckie Exp $$
 */
@Service
public class ABC {

  @Autowired
  private BeanController beanController;

  @PostConstruct
  public void init() {
    System.out.println("------------------------------------");
  }

  public void test() {
    beanController.addBean("zhangsan");
  }
}
