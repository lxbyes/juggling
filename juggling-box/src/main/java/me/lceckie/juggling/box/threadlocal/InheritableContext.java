package me.lceckie.juggling.box.threadlocal;

/**
 * @author Leckie
 * @date 2019-10-14
 */
public abstract class InheritableContext {

  private final static ThreadLocal<String> context = new InheritableThreadLocal<>();

  public static void set(String value) {
    context.set(value);
  }

  public static String get() {
    return context.get();
  }

  public static void clear() {
    context.remove();
  }
}
