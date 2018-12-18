package me.leckie.dynamicbeanload;

import javax.annotation.PostConstruct;
import me.leckie.dynamicbeanload.service.OOOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Leckie
 * @version $Id: BeanConfiguration.java, v0.1 2018/11/26 10:47 Leckie Exp $$
 */
@Configuration
public class BeanConfiguration {

  @Autowired
  private Student student;

  @PostConstruct
  private void init() {
    System.out.println(student.getName());
  }

  @Bean
  public StudentFactoryBean student() {
    return new StudentFactoryBean();
  }

  @Bean
  public OOOService oooService() {
    return new OOOService();
  }

}
