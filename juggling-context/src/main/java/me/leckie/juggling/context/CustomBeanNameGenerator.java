package me.leckie.juggling.context;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

/**
 * @author laixianbo
 * @version $Id: CustomBeanNameGenerator.java, v0.1 2018/11/19 14:44 laixianbo Exp $$
 */
public class CustomBeanNameGenerator implements BeanNameGenerator {

  @Override
  public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
    return definition.getBeanClassName();
  }
}
