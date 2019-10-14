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
public class InheritableContextTest {

  private String value = "some string";

  @Before
  public void setUp() {
    InheritableContext.set(value);
  }

  @After
  public void clearContext() {
    InheritableContext.clear();
  }

  @Test
  public void shouldGetRightWhenSingleThread() {
    Assert.assertEquals(value, InheritableContext.get());
  }

  @Test
  public void shouldGetNonNullWhenInChildThread() throws InterruptedException {

    final Map<String, String> wrapper = new HashMap<>();

    Thread childThread = new Thread(() -> {
      wrapper.put("value", InheritableContext.get());
    });
    childThread.start();
    childThread.join();
    Assert.assertNotNull(wrapper.get("value"));
    Assert.assertEquals(value, wrapper.get("value"));
  }
}
