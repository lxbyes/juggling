package me.leckie.dynamicbeanload;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author laixianbo
 * @version $Id: StudentFactoryBean.java, v0.1 2018/11/26 10:44 laixianbo Exp $$
 */
public class StudentFactoryBean implements FactoryBean<Student> {

  @Override
  public Student getObject() {
    Student student = new Student();
    student.setAge(28);
    student.setGender("M");
    student.setName("Leckie");
    return student;
  }

  @Override
  public Class<?> getObjectType() {
    return Student.class;
  }

  @Override
  public boolean isSingleton() {
    return true;
  }
}
