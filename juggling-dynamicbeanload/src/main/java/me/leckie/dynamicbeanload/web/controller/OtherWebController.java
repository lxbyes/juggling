package me.leckie.dynamicbeanload.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.leckie.dynamicbeanload.Student;
import me.leckie.dynamicbeanload.service.DynamicWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leckie
 * @version $Id: OtherWebController.java, v0.1 2018/11/19 15:49 Leckie Exp $$
 */
@RequestMapping("/others")
@RestController
public class OtherWebController {

  @Autowired
  private DynamicWebService dynamicWebService;

  @RequestMapping("/**")
  public Object handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
    // CustomHttpServletRequest customHttpServletRequest = new CustomHttpServletRequest(request);
    Object mv = dynamicWebService.handleRequest(request, response);
    return mv;
  }

  @RequestMapping("/mappings")
  public Object mappings() {
    return dynamicWebService.getMappings();
  }

  @PutMapping("/students")
  public Object students(@RequestBody Student student) {
    return student;
  }

}
