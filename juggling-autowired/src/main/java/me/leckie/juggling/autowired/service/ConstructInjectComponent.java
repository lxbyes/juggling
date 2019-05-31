package me.leckie.juggling.autowired.service;

import org.springframework.stereotype.Component;

/**
 * @author Leckie
 * @version $Id: ConstructInjectComponent.java, v0.1 2018/11/12 11:21 Leckie Exp $$
 */
@Component
public class ConstructInjectComponent {

  private InjectService injectService;

  private ConfigurableProperties configurableProperties;

  public ConstructInjectComponent(InjectService injectService, ConfigurableProperties configurationProperties) {
    this.injectService = injectService;
    this.configurableProperties = configurationProperties;
    configurationProperties.print();
  }

  public void hello(String name) {
    injectService.hello(name);
  }

}
