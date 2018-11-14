package me.leckie.juggling.context;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

/**
 * @author laixianbo
 * @version $Id: CustomURLClassLoader.java, v0.1 2018/11/14 16:32 laixianbo Exp $$
 */
public class CustomURLClassLoader extends URLClassLoader {

  public CustomURLClassLoader(URL[] urls, ClassLoader parent) {
    super(urls, parent);
  }

  public CustomURLClassLoader(URL[] urls) {
    super(urls);
  }

  public CustomURLClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
    super(urls, parent, factory);
  }

  @Override
  public void addURL(URL url) {
    super.addURL(url);
  }

}
