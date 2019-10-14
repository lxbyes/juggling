package me.lceckie.juggling.box.threadlocal;

/**
 * @author laixianbo_cd
 * @date 2019-10-14
 */
public abstract class SomeContext {

    private final static ThreadLocal<String> context = new ThreadLocal<>();

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
