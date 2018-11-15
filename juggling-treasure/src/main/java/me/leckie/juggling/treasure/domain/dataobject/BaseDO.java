package me.leckie.juggling.treasure.domain.dataobject;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Leckie
 * @version $Id: BaseDO.java, v0.1 2018/8/17 16:36 Leckie Exp $$
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseDO implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @CreationTimestamp
  private LocalDateTime gmtCreate;

  @UpdateTimestamp
  private LocalDateTime gmtModified;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getGmtCreate() {
    return gmtCreate;
  }

  public void setGmtCreate(LocalDateTime gmtCreate) {
    this.gmtCreate = gmtCreate;
  }

  public LocalDateTime getGmtModified() {
    return gmtModified;
  }

  public void setGmtModified(LocalDateTime gmtModified) {
    this.gmtModified = gmtModified;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(ToStringStyle.JSON_STYLE);
  }
}
