package me.leckie.dynamicbeanload.classloader;

import java.net.URL;
import java.net.URLClassLoader;

public class ExtensibleClassLoader extends URLClassLoader {

  public ExtensibleClassLoader(ClassLoader parent) {
    super(new URL[]{}, parent);
  }

  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    if (true) {
      return super.findClass(name);
    } else {
      throw new ClassNotFoundException();
    }
  }
}
