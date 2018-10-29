/**
 * BBD Service Inc All Rights Reserved @2018
 */
package me.leckie.juggling.common;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author laixianbo
 * @version $Id: TestSome.java, v0.1 2018/10/26 13:51 laixianbo Exp $$
 */
public class TestSome {

  public static void printMap(Map<? extends Object, ? extends Object> map) {
    Objects.requireNonNull(map, "map不能为空");
    System.out.println("----->" + map.getClass().getSimpleName());
    for (Object key : map.keySet()) {
      System.out.println(key);
    }

  }

  public static void main(String[] args) {
    List<Integer> elements = Lists.newArrayList(8885, 8887, 8888, 8883, 8884, 8882, 8888);
    Map<Integer, Integer> hashMap = Maps.newHashMap();
    elements.forEach(ele -> hashMap.put(ele, ele));
    printMap(hashMap);
    System.out.println("----------------");

    Map<Integer, Integer> linkedHashMap = Maps.newLinkedHashMap();
    elements.forEach(ele -> linkedHashMap.put(ele, ele));
    printMap(linkedHashMap);
    System.out.println("----------------");

    Map<Integer, Integer> concurrentMap = Maps.newConcurrentMap();
    elements.forEach(ele -> concurrentMap.put(ele, ele));
    printMap(concurrentMap);
    System.out.println("----------------");

    Map<Integer, Integer> treeMap = Maps.newTreeMap((o1, o2) -> o2 - o1);
    elements.forEach(ele -> treeMap.put(ele, ele));
    printMap(treeMap);
    System.out.println("----------------");

  }

}
