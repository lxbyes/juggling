package me.leckie.juggling.autowired.service;

import org.springframework.stereotype.Component;

/**
 * @author laixianbo
 * @version $Id: ConstructInjectComponent.java, v0.1 2018/11/12 11:21 laixianbo Exp $$
 */
@Component
public class ConstructInjectComponent {

  private InjectService injectService;

  public ConstructInjectComponent(InjectService injectService) {
    this.injectService = injectService;
  }

  public void hello(String name) {
    injectService.hello(name);
  }

}
