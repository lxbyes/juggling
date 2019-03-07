package me.leckie.admin.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leckie
 * @version $Id: TestController.java, v0.1 2019/2/19 15:13 john Exp $$
 */
@RestController
@RequestMapping("data")
public class TestController {

  @GetMapping("/{id}")
  public Map<String, Object> data(@PathVariable String id) {
    Map<String, Object> data = new HashMap<>();
    data.put("supplyId", id);
    List<Map<String, Object>> list = new ArrayList<>();
    data.put("orders", list);
    for (int i = 0; i < 20000; i++) {
      Map<String, Object> item = new HashMap<>();
      item.put("id", UUID.randomUUID().toString());
      for (int j = 0; j < 20; j++) {
        item.put(UUID.randomUUID().toString(), "结算单/销售单无对应的验收订单");
      }
      list.add(item);
    }
    return data;
  }
}
