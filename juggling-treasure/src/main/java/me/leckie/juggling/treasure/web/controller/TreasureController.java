/**
 * BBD Service Inc All Rights Reserved @2018
 */
package me.leckie.juggling.treasure.web.controller;

import me.leckie.juggling.treasure.model.Treasure;
import me.leckie.juggling.treasure.service.TreasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laixianbo
 * @version $Id: TreasureController.java, v0.1 2018/8/17 16:12 laixianbo Exp $$
 */
@RestController
@RequestMapping("/treasures")
public class TreasureController {

  @Autowired
  private TreasureService treasureService;

  @GetMapping("/random")
  public Treasure random() {
    return treasureService.random();
  }

}
