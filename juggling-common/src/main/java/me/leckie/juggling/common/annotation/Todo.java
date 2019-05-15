package me.leckie.juggling.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Leckie
 * @version $Id: Todo.java, v0.1 2019/5/5 17:13 john Exp $$
 */
@Inherited
@Documented
@Retention(RetentionPolicy.SOURCE)
public @interface Todo {

  public enum Priority {LOW, MEDIUM, HIGH}

  public enum Status {STARTED, NOT_STARTED}

  String author() default "John";

  Priority priority() default Priority.MEDIUM;

  Status status() default Status.NOT_STARTED;

}
