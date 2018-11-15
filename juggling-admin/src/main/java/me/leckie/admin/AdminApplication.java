package me.leckie.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import java.util.Arrays;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Leckie
 * @version $Id: AdminApplication.java, v0.1 2018/11/9 10:06 Leckie Exp $$
 */
@SpringBootApplication
@EnableAdminServer
public class AdminApplication implements CommandLineRunner, ApplicationRunner {

  public static void main(String[] args) {
    SpringApplication.run(AdminApplication.class, args);
  }

  @Override
  public void run(String... args) {
    System.out.println("-----------------------");
    System.out.println(Arrays.asList(args));
    System.out.println("-----------------------");
  }

  @Override
  public void run(ApplicationArguments args) {
    System.out.println("ApplicationArguments-----------------------");
    System.out.println(args.getNonOptionArgs());
    System.out.println(args.getOptionNames());
    System.out.println(Arrays.asList(args.getSourceArgs()));
    System.out.println("ApplicationArguments-----------------------");
  }
}
