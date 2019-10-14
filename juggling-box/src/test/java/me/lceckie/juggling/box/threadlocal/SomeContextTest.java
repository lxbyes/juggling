package me.lceckie.juggling.box.threadlocal;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author laixianbo_cd
 * @date 2019-10-14
 */
public class SomeContextTest {

  @Test
  public void shouldGetRightWhenSingleThread() {
    String value = "This will only in Single Thread!";
    SomeContext.set(value);
    Assert.assertEquals(value, SomeContext.get());
  }

  @Test
  public void shouldGetNullWhenInChildThread() throws InterruptedException {
    final Map<String, String> wrapper = new HashMap<>();
    String value = "This will not in child thread!";

    SomeContext.set(value);
    Thread childThread = new Thread(new Runnable() {
      @Override
      public void run() {
        String value = SomeContext.get();
        wrapper.put("value", value);
      }
    });
    childThread.start();
    childThread.join();
    Assert.assertNull(wrapper.get("value"));
  }


}