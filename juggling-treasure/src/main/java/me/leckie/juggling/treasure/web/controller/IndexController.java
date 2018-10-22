/**
 * BBD Service Inc All Rights Reserved @2018
 */
package me.leckie.juggling.treasure.web.controller;

import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laixianbo
 * @version $Id: IndexController.java, v0.1 2018/8/17 14:40 laixianbo Exp $$
 */
@RestController
@RequestMapping("/index")
public class IndexController {

  @GetMapping
  public String index(HttpSession session) {
    session.setAttribute("name", "Leckie");
    return "Hello, World!";
  }

}
