package me.leckie.juggling.treasure.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import me.leckie.juggling.treasure.model.Treasure;
import me.leckie.juggling.treasure.service.TreasureService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Leckie
 * @version $Id: TreasureControllerTests.java, v0.1 2018/8/17 16:14 Leckie Exp $$
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TreasureController.class)
public class TreasureControllerTests {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private TreasureService treasureService;

  @Test
  public void random() throws Exception {
    Treasure treasure = new Treasure();
    BDDMockito.given(treasureService.random()).willReturn(treasure);
    mvc.perform(get("/treasures/random")).andExpect(status().isOk()).andExpect(content().json(treasure.toString()));
  }
}