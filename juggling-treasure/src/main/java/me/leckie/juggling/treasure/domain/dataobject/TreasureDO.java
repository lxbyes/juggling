/**
 * BBD Service Inc All Rights Reserved @2018
 */
package me.leckie.juggling.treasure.domain.dataobject;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import me.leckie.juggling.treasure.model.Gender;

/**
 * @author laixianbo
 * @version $Id: TreasureDO.java, v0.1 2018/8/17 16:44 laixianbo Exp $$
 */
@Entity
@Table(name = "treasure")
@Data
public class TreasureDO extends BaseDO {

  private String name;

  private Gender gender;

  private LocalDate birthday;

}
