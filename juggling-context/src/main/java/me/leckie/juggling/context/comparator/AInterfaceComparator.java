package me.leckie.juggling.context.comparator;

import java.util.Comparator;
import me.leckie.juggling.facade.AInterface;
import org.springframework.core.annotation.Order;

/**
 * @author laixianbo
 * @version $Id: AInterfaceComparator.java, v0.1 2018/11/15 17:11 laixianbo Exp $$
 */
public class AInterfaceComparator implements Comparator<AInterface> {

  private static final AInterfaceComparator instance = new AInterfaceComparator();

  private AInterfaceComparator() {
  }

  public static final AInterfaceComparator getInstance() {
    return instance;
  }

  @Override
  public int compare(AInterface o1, AInterface o2) {
    Order order1 = o1.getClass().getDeclaredAnnotation(Order.class);
    Order order2 = o2.getClass().getDeclaredAnnotation(Order.class);
    int i = order1 != null ? order1.value() : Integer.MAX_VALUE;
    int j = order2 != null ? order2.value() : Integer.MAX_VALUE;
    return i - j;
  }

}
