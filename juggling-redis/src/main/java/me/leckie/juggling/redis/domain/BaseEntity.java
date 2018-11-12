package me.leckie.juggling.redis.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author laixianbo
 * @version $Id: BaseEntity.java, v0.1 2018/10/23 9:42 laixianbo Exp $$
 */
public class BaseEntity implements Serializable {

  public BaseEntity() {
    this.id = UUID.randomUUID().toString();
    this.gmtCreate = LocalDateTime.now();
  }

  private String id;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
  private LocalDateTime gmtCreate;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LocalDateTime getGmtCreate() {
    return gmtCreate;
  }

  public void setGmtCreate(LocalDateTime gmtCreate) {
    this.gmtCreate = gmtCreate;
  }

}
