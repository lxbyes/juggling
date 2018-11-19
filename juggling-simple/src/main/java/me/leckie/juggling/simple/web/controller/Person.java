package me.leckie.juggling.simple.web.controller;

import java.io.Serializable;

/**
 * @author laixianbo
 * @version $Id: Person.java, v0.1 2018/11/19 18:35 laixianbo Exp $$
 */
public class Person implements Serializable {

  private String name;

  private String city;

  private Integer age;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }
}
