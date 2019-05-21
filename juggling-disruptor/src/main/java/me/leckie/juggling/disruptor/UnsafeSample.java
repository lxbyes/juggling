package me.leckie.juggling.disruptor;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import sun.misc.Unsafe;

/**
 * @author laixianbo
 * @version $Id: UnsafeSample.java, v0.1 2019/5/21 19:26 john Exp $$
 */
public class UnsafeSample {

  public static void main(String[] args) throws InstantiationException, NoSuchFieldException, IllegalAccessException {
    Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
    unsafeField.setAccessible(true);
    Unsafe unsafe = (Unsafe) unsafeField.get(null);
    PrivateClass o = (PrivateClass) unsafe.allocateInstance(PrivateClass.class);
    System.out.println(o);
    o.say();
    Field messageField = PrivateClass.class.getDeclaredField("message");
    messageField.setAccessible(true);
    messageField.set(o, "Hello, World!");
    o.say();
    System.out.println(Modifier.isStatic(unsafeField.getModifiers()));
  }

}
