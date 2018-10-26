/**
 * BBD Service Inc All Rights Reserved @2018
 */
package me.leckie.juggling.jdk8feature.funinterface;

import java.io.Serializable;

/**
 * @author laixianbo
 * @version $Id: Employee.java, v0.1 2018/10/24 18:18 laixianbo Exp $$
 */
public class Employee implements Serializable {

  private Integer id;

  private Integer age;

  private String gender;

  private String name;

  public Employee(Integer id, Integer age, String gender, String name) {
    this.id = id;
    this.age = age;
    this.gender = gender;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Employee{" +
        "id=" + id +
        ", age=" + age +
        ", gender='" + gender + '\'' +
        ", name='" + name + '\'' +
        '}';
  }

}
