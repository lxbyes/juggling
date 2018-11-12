package me.leckie.juggling.hive;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author laixianbo
 * @version $Id: JdbcHiveTests.java, v0.1 2018/8/21 14:55 laixianbo Exp $$
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcHiveTests {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void test() {
  }
}
