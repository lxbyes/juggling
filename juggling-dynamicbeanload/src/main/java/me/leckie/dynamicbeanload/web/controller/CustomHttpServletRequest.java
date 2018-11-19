package me.leckie.dynamicbeanload.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author laixianbo
 * @version $Id: CustomHttpServletRequest.java, v0.1 2018/11/19 17:58 laixianbo Exp $$
 */
public class CustomHttpServletRequest extends HttpServletRequestWrapper {

  /**
   * Constructs a request object wrapping the given request.
   *
   * @param request The request to wrap
   * @throws IllegalArgumentException if the request is null
   */
  public CustomHttpServletRequest(HttpServletRequest request) {
    super(request);
  }

  @Override
  public String getRequestURI() {
    return super.getRequestURI().replace("/others", "");
  }

  @Override
  public StringBuffer getRequestURL() {
    return new StringBuffer(super.getRequestURL().toString().replace("/other", ""));
  }
}
