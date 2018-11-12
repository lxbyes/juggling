package me.leckie.juggling.treasure.model;

import java.io.Serializable;
import java.time.LocalDate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author laixianbo
 * @version $Id: Treasure.java, v0.1 2018/8/17 16:07 laixianbo Exp $$
 */
public class Treasure implements Serializable {

  private String name;

  private Gender gender;

  private LocalDate birthday;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
