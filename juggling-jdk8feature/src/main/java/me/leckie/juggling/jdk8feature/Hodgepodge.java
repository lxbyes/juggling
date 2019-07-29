package me.leckie.juggling.jdk8feature;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Spliterators;

/**
 * @author Leckie
 * @version Hodgepodge: Hodgepodge.java, v0.1 2019/6/24 10:06 john Exp $$
 */
public class Hodgepodge {

  public static void main(String[] args) {
    Path path = Paths.get("D://bbd");
    System.out.println(path);
    System.out.println(File.separatorChar);
  }
}
