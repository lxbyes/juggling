/**
 * BBD Service Inc All Rights Reserved @2018
 */
package me.leckie.juggling.treasure.web.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author laixianbo
 * @version $Id: EmbdedIndexControllerTests.java, v0.1 2018/8/17 15:26 laixianbo Exp $$
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class EmbeddedIndexControllerTests {

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  public void index() {
    String body = testRestTemplate.getForObject("/index", String.class);
    assertThat(body).isEqualTo("Hello, World!");
  }

}
