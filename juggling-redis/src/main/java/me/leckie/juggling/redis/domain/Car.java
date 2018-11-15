package me.leckie.juggling.redis.domain;

/**
 * @author Leckie
 * @version $Id: Car.java, v0.1 2018/10/23 9:48 Leckie Exp $$
 */
public class Car extends BaseEntity {

  private String brand;

  private String color;

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }
}
