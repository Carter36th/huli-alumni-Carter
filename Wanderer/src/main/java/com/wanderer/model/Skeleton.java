package com.wanderer.model;

import javax.imageio.ImageIO;
import java.io.File;
import org.springframework.beans.factory.annotation.Value;

public class Skeleton extends Character {

  @Value("${RESOURCES_PATH}")
  private String pathVariable;

  public Skeleton() {
    this.HP = 2 * this.LVL * (int) (6.0 * Math.random() + 1);
    this.DP = this.LVL / 2 * (int) (6.0 * Math.random() + 1);
    this.SP = this.LVL * (int) (6.0 * Math.random() + 1);
    charID = numOfNPCs;
    numOfNPCs++;

    try {
      image = ImageIO.read(new File(
          pathVariable + "\\png\\skeleton.png"));
    } catch (Exception e) {
      System.out.println("Cannot find skeletonImg");
    }
  }

  public void NPCLvlUp() {
    this.LVL++;
    this.HP = 2 * this.LVL * (int) (6.0 * Math.random() + 1);
    this.DP = this.LVL / 2 * (int) (6.0 * Math.random() + 1);
    this.SP = this.LVL * (int) (6.0 * Math.random() + 1);
  }
}
