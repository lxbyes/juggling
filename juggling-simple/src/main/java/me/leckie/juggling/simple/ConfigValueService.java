package me.leckie.juggling.simple;

import me.leckie.juggling.facade.listener.PostConstructAndPreDestroyListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Leckie
 * @version $Id: ConfigValueService.java, v0.1 2018/11/16 11:03 Leckie Exp $$
 */
@Service
public class ConfigValueService implements PostConstructAndPreDestroyListener {

  @Value("${me.leckie.hello.world: Hello from Class ConfigValueService...}")
  private String helloWorld;

  public String say() {
    System.out.println(helloWorld);
    return helloWorld;
  }

}
