package me.leckie.juggling.treasure.dao;

import java.time.LocalDate;
import java.util.List;
import me.leckie.juggling.treasure.domain.dataobject.TreasureDO;
import me.leckie.juggling.treasure.model.Gender;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author laixianbo
 * @version $Id: TreasureDAOTests.java, v0.1 2018/8/17 16:47 laixianbo Exp $$
 */
@RunWith(SpringRunner.class)
// @DataJpaTest
@SpringBootTest
// @Transactional(propagation =


public class TreasureDAOTests {

  //@Autowired
  //private TestEntityManager entityManager;

  @Autowired
  private TreasureDAO treasureDAO;

  @Test
  public void insert() {
    TreasureDO treasureDO = new TreasureDO();
    treasureDO.setName("Leckie");
    treasureDO.setBirthday(LocalDate.of(1990, 9, 5));
    treasureDO.setGender(Gender.MALE);
    // this.entityManager.persist(treasureDO);
    treasureDAO.save(treasureDO);
    List<TreasureDO> all = this.treasureDAO.findAll();
    System.out.println(all);
    Assert.assertTrue(all.size() > 0);
  }

}