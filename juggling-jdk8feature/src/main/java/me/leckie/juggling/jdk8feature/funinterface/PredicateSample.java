/**
 * BBD Service Inc All Rights Reserved @2018
 */
package me.leckie.juggling.jdk8feature.funinterface;

import java.util.function.Predicate;

/**
 * @author laixianbo
 * @version $Id: PredicateSample.java, v0.1 2018/10/24 18:01 laixianbo Exp $$
 */
public class PredicateSample {

  public static void main(String[] args) {

    Predicate<Employee> adultPredicate = employee -> employee.getAge() >= 18;
    Predicate<Employee> malePredicate = employee -> "M".equalsIgnoreCase(employee.getGender());
    Predicate<Employee> adultMalePredicate = employee -> employee.getAge() >= 18 && "M"
        .equalsIgnoreCase(employee.getGender());//adultPredicate.and(malePredicate);
    System.out.println(adultPredicate.test(new Employee(1, 20, "M", "Leckie")));
  }

}
