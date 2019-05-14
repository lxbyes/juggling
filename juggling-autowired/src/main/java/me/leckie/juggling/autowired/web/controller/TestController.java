package me.leckie.juggling.autowired.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laixianbo
 * @version $Id: TestController.java, v0.1 2019/5/14 17:58 john Exp $$
 */
@RestController
public class TestController {

  @GetMapping("tests{id}")
  public String testPathVariable(@PathVariable String id) {
    return id;
  }

}
