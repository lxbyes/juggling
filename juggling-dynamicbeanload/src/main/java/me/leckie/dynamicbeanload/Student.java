package me.leckie.dynamicbeanload;

import java.io.Serializable;

/**
 * @author laixianbo
 * @version $Id: Student.java, v0.1 2018/11/20 9:45 laixianbo Exp $$
 */
public class Student implements Serializable {

  private String name;

  private Integer age;

  private String gender;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }
}
