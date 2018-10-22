/**
 * BBD Service Inc All Rights Reserved @2018
 */
package me.leckie.juggling.treasure.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import me.leckie.juggling.treasure.BaseUnitTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author laixianbo
 * @version $Id: IndexControllerTests.java, v0.1 2018/8/17 14:42 laixianbo Exp $$
 */
@AutoConfigureMockMvc
public class IndexControllerTests extends BaseUnitTests {

  @Autowired
  private MockMvc mvc;

  @Test
  public void index() throws Exception {
    mvc.perform(get("/index")).andExpect(status().isOk()).andExpect(content().string("Hello, World!"));
  }

}
