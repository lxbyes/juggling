package me.leckie.dynamicbeanload.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.stereotype.Service;

@Service
public class DynamicService {


  @PostConstruct
  private void postConstruct() {
    System.out.println("----------- " + getClass().getName() + " postConstruct");
  }

  @PreDestroy
  private void PreDestroy() {
    System.out.println("----------- " + getClass().getName() + " PreDestroy");
  }
}
