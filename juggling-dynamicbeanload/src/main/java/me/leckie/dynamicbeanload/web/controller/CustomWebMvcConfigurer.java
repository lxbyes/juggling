package me.leckie.dynamicbeanload.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CustomWebMvcConfigurer implements WebMvcConfigurer {

  @Autowired
  private List<HttpMessageConverter> httpMessageConverters;

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.addAll(converters);
  }


}
