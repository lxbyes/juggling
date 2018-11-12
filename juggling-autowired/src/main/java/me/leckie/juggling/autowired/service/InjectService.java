package me.leckie.juggling.autowired.service;

import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author laixianbo
 * @version $Id: InjectService.java, v0.1 2018/11/12 11:05 laixianbo Exp $$
 */
@Service
public class InjectService {

  private Logger logger = LoggerFactory.getLogger(InjectService.class);

  @PostConstruct
  private void postConstruct() {
    logger.info("postConstruct");
  }

  public void hello(String name) {
    System.out.println("Hello " + name);
  }

}
