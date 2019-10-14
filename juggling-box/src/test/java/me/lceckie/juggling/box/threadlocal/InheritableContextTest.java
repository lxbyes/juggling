package me.lceckie.juggling.box.threadlocal;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author laixianbo_cd
 * @date 2019-10-14
 */
public class InheritableContextTest {

  @Test
  public void shouldGetRightWhenSingleThread() {
    String value = "This will only in Single Thread!";
    InheritableContext.set(value);
    Assert.assertEquals(value, InheritableContext.get());
  }

  @Test
  public void shouldGetNonNullWhenInChildThread() throws InterruptedException {
    final Map<String, String> wrapper = new HashMap<>();
    String value = "This will not in child thread!";

    InheritableContext.set(value);
    Thread childThread = new Thread(new Runnable() {
      @Override
      public void run() {
        String value = InheritableContext.get();
        wrapper.put("value", value);
      }
    });
    childThread.start();
    childThread.join();
    Assert.assertNotNull(wrapper.get("value"));
    Assert.assertEquals(value, wrapper.get("value"));
  }
}
