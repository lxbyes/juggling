package me.leckie.dynamicbeanload.web.controller;

import java.util.List;
import me.leckie.dynamicbeanload.service.DynamicbeanloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laixianbo
 * @version $Id: BeanController.java, v0.1 2018/11/13 10:55 laixianbo Exp $$
 */
@RestController
@RequestMapping("/beans")
public class BeanController {

  @Autowired
  private DynamicbeanloadService dynamicbeanloadService;

  @GetMapping
  public List<Object> listBeans() {
    return dynamicbeanloadService.listBeans();
  }

  @PostMapping("/{beanName}")
  public void addBean(@PathVariable String beanName) {
    dynamicbeanloadService.addBean(beanName);
  }

  @PutMapping("/{beanName}")
  public void updateBean(@PathVariable String beanName) {
    dynamicbeanloadService.updateBean(beanName);
  }

  @DeleteMapping("/{beanName}")
  public void deleteBean(@PathVariable String beanName) {
    dynamicbeanloadService.deleteBean(beanName);
  }

  @GetMapping("/{beanName}")
  public Object getBean(@PathVariable String beanName) {
    return dynamicbeanloadService.getBean(beanName);
  }

  @GetMapping("/aservice")
  public Object getBean() {
    return dynamicbeanloadService.getAService();
  }
}
