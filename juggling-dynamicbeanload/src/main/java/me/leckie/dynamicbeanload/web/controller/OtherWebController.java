package me.leckie.dynamicbeanload.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.leckie.dynamicbeanload.service.DynamicWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author Leckie
 * @version $Id: OtherWebController.java, v0.1 2018/11/19 15:49 Leckie Exp $$
 */
@RequestMapping("/others")
@RestController
public class OtherWebController {

  @Autowired
  private RequestMappingHandlerMapping requestMappingHandlerMapping;

  @Autowired
  private DynamicWebService dynamicWebService;

  @RequestMapping("/**")
  public Object handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
    CustomHttpServletRequest customHttpServletRequest = new CustomHttpServletRequest(request);
    HandlerExecutionChain mappingHandler = requestMappingHandlerMapping.getHandler(request);
    Object result = dynamicWebService.handleRequest(customHttpServletRequest, response);
    return request;
  }

  @RequestMapping("/mappings")
  public Object mappings() {
    return dynamicWebService.getMappings();
  }

}
