package me.leckie.juggling.treasure.dao;

import me.leckie.juggling.treasure.domain.dataobject.TreasureDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Leckie
 * @version $Id: TreasureDAO.java, v0.1 2018/8/17 16:46 Leckie Exp $$
 */
public interface TreasureDAO extends JpaRepository<TreasureDO, Long> {

}
