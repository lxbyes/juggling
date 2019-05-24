package me.lceckie.juggling.box.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author Leckie
 * @version $Id: TimingMessage.java, v0.1 2019/5/24 10:31 john Exp $$
 */
public class TimingMessage implements Delayed {

  private String message;

  // ms
  private long executeTime;

  public TimingMessage(String message) {
    this(message, 0L);
  }

  public TimingMessage(String message, long delay) {
    this.message = message;
    this.executeTime = System.currentTimeMillis() + delay;
  }

  @Override
  public long getDelay(TimeUnit unit) {
    return unit.convert(executeTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
  }

  @Override
  public int compareTo(Delayed o) {
    return Long.compare(getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public long getExecuteTime() {
    return executeTime;
  }

  public void setExecuteTime(long executeTime) {
    this.executeTime = executeTime;
  }
}
