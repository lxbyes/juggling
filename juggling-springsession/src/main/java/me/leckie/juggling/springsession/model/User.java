package me.leckie.juggling.springsession.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author Leckie
 * @version User.java, v0.1 2019-07-24 10:59
 */
@Data
public class User implements Serializable {

  private Long id;

  private String name;

  private String nickName;

  private String username;

  @JsonIgnore
  private String password;

  private Gender gender;

  private List<String> roles;
}
