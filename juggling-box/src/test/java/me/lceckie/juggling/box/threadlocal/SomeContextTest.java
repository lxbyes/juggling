package me.lceckie.juggling.box.threadlocal;

import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Leckie
 * @date 2019-10-14
 */
public class SomeContextTest {

  private String value = "some string";

  @Before
  public void setUp() {
    SomeContext.set(value);
  }

  @After
  public void clearContext() {
    SomeContext.clear();
  }

  @Test
  public void shouldGetRightWhenSingleThread() {
    Assert.assertEquals(value, SomeContext.get());
  }

  @Test
  public void shouldGetNullWhenInChildThread() throws InterruptedException {

    final Map<String, String> wrapper = new HashMap<>();

    Thread childThread = new Thread(() -> {
      wrapper.put("value", SomeContext.get());
    });
    childThread.start();
    childThread.join();
    Assert.assertNull(wrapper.get("value"));
  }
}