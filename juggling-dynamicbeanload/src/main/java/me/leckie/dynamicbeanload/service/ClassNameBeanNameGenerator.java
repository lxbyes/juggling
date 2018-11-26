package me.leckie.dynamicbeanload.service;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.util.StringUtils;

/**
 * @author laixianbo
 * @version $Id: ClassNameBeanNameGenerator.java, v0.1 2018/11/26 10:28 laixianbo Exp $$
 */
public class ClassNameBeanNameGenerator extends AnnotationBeanNameGenerator {

  @Override
  public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
    if (definition instanceof AnnotatedBeanDefinition) {
      String beanName = determineBeanNameFromAnnotation((AnnotatedBeanDefinition) definition);
      if (StringUtils.hasText(beanName)) {
        // Explicit bean name found.
        return beanName;
      }
    }
    return definition.getBeanClassName();
  }
}
