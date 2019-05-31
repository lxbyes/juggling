package me.leckie.juggling.autowired.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Leckie
 * @version 1: ConfigurableProperties.java, v0.1 2019/5/31 13:26 john Exp $$
 */
@ConfigurationProperties
@Component
public class ConfigurableProperties {

  private String nihao = "";

  private Properties properties = new Properties();

  private Hello hello = new Hello();

  public void print() {
    System.out.println(nihao);
    System.out.println(properties.getMap());
    System.out.println(hello.getA() + ", " + hello.getB() + ", " + hello.getC());
  }

  class Properties {

    private Map<String, String> map = new HashMap<>();

    public Map<String, String> getMap() {
      return map;
    }

    public void setMap(Map<String, String> map) {
      this.map = map;
    }
  }

  class Hello {

    private Integer a = 0;

    private Integer b = 0;

    private Integer c = 0;

    public Integer getA() {
      return a;
    }

    public void setA(Integer a) {
      this.a = a;
    }

    public Integer getB() {
      return b;
    }

    public void setB(Integer b) {
      this.b = b;
    }

    public Integer getC() {
      return c;
    }

    public void setC(Integer c) {
      this.c = c;
    }

  }

  public String getNihao() {
    return nihao;
  }

  public void setNihao(String nihao) {
    this.nihao = nihao;
  }

  public Properties getProperties() {
    return properties;
  }

  public void setProperties(Properties properties) {
    this.properties = properties;
  }

  public Hello getHello() {
    return hello;
  }

  public void setHello(Hello hello) {
    this.hello = hello;
  }
}
