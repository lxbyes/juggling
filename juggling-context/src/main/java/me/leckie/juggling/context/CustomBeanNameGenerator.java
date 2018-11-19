package me.leckie.juggling.context;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

/**
 * @author Leckie
 * @version $Id: CustomBeanNameGenerator.java, v0.1 2018/11/19 14:44 Leckie Exp $$
 */
public class CustomBeanNameGenerator implements BeanNameGenerator {

  @Override
  public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
    return definition.getBeanClassName();
  }
}
