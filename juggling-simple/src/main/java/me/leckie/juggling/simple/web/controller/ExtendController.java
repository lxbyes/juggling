package me.leckie.juggling.simple.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.leckie.juggling.facade.WebController;
import me.leckie.juggling.facade.listener.PostConstructAndPreDestroyListener;
import me.leckie.juggling.simple.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leckie
 * @version $Id: ExtendController.java, v0.1 2018/11/13 15:47 Leckie Exp $$
 */
@RestController
public class ExtendController implements WebController, PostConstructAndPreDestroyListener {

  @Autowired
  private AService aService;

  @GetMapping("/a")
  public String hello() {
    return aService.a(getClass().getName());
  }

  @RequestMapping("/b")
  public String world() {
    return "Hello, World!";
  }

  @PostMapping(value = {"c", "/d"}, params = {"a", "v"})
  public String post() {
    return "post";
  }

  @PutMapping("/person")
  public Object putPerson(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    Person person = objectMapper.readValue(request.getInputStream(), Person.class);
    return person;
  }

  @PostMapping("/person")
  public Person putPerson(@RequestBody Person person) {
    return person;
  }

  @GetMapping("/map")
  public Object getMap() {
    Map<String, Object> map = new HashMap<>();
    map.put("name", "李四");
    map.put("age", 30);
    return map;
  }

}
