package com.wanderer.model;

import javax.imageio.ImageIO;
import java.io.File;

public class Boss extends Character {

  public Boss() {
    this.HP = 2 * this.LVL * (int) (6.0 * Math.random() + 1) + (int) (6.0 * Math.random() + 1);
    this.DP =
        this.LVL / 2 * (int) (6.0 * Math.random() + 1) + ((int) (6.0 * Math.random() + 1) / 2);
    this.SP = this.LVL * (int) (6.0 * Math.random() + 1) + this.LVL;
    charID = numOfNPCs;
    numOfNPCs++;

    try {
      image = ImageIO.read(new File(
          "D:\\Coding\\GreenFox\\huli-Wanderer\\huli-alumni-Carter\\Wanderer\\src\\main\\resources\\png\\boss.png"));
    } catch (Exception e) {
      System.out.println("Cannot find bossImg");
    }
  }

  public void NPCLvlUp() {
    this.LVL++;
    this.HP = 2 * this.LVL * (int) (6.0 * Math.random() + 1) + (int) (6.0 * Math.random() + 1);
    this.DP =
        this.LVL / 2 * (int) (6.0 * Math.random() + 1) + ((int) (6.0 * Math.random() + 1) / 2);
    this.SP = this.LVL * (int) (6.0 * Math.random() + 1) + this.LVL;
  }
}
