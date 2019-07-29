package me.leckie.juggling.springsession.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Leckie
 * @version Gender.java, v0.1 2019-07-24 11:00
 */
public enum Gender {

  MALE("男"), FEMALE("女");

  private String description;

  Gender(String description) {
    this.description = description;
  }

  @JsonSerialize
  @Override
  public String toString() {
    return this.description;
  }
}
