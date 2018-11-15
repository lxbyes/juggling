package me.leckie.juggling.simple.web.controller;

import me.leckie.juggling.facade.listener.PostConstructAndPreDestroyListener;
import me.leckie.juggling.simple.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laixianbo
 * @version $Id: ExtendController.java, v0.1 2018/11/13 15:47 laixianbo Exp $$
 */
@RestController
@RequestMapping("/others")
public class ExtendController implements PostConstructAndPreDestroyListener {

  @Autowired
  private AService aService;

  @GetMapping("/a")
  public String hello() {
    return aService.a(getClass().getName());
  }

  @RequestMapping("/b")
  public String world() {
    return "Hello, World!";
  }

  @PostMapping(value = {"c", "/d"}, params = {"a", "v"})
  public String post() {
    return "post";
  }

}