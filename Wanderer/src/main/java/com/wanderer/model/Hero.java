package com.wanderer.model;

import com.wanderer.service.implementation.MapGeneratorImp;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import org.springframework.beans.factory.annotation.Value;

public class Hero extends Character {

  private BufferedImage heroDown;
  private BufferedImage heroUp;
  private BufferedImage heroLeft;
  private BufferedImage heroRight;
  private int heroPositionX;
  private int HeroPositionY;
  private Character target;

  @Value("${RESOURCES_PATH}")
  private String pathVariable;


  public Hero() {
    this.MaxHP = 38;
    this.HP = 20 + 3 * (int) (6.0 * Math.random() + 1);
    this.DP = 2 * (int) (6.0 * Math.random() + 1);
    this.SP = 5 + (int) (6.0 * Math.random() + 1);

    this.heroPositionX = 0;
    this.HeroPositionY = 0;
    try {
      heroDown = ImageIO.read(new File(
          pathVariable + "\\png\\hero-down.png"));
      heroUp = ImageIO.read(new File(
          pathVariable + "\\png\\hero-up.png"));
      heroLeft = ImageIO.read(new File(
          pathVariable + "\\png\\hero-left.png"));
      heroRight = ImageIO.read(new File(
          pathVariable + "\\png\\hero-right.png"));
    } catch (Exception e) {
      System.out.println("Hero position image not found.");
    }
  }

  @Override
  public void battle(Character npc) {
    target = npc;
  }

  public boolean HeroStrike(MapGeneratorImp map) {
    int SV = this.SP + (2 * (int) (6.0 * Math.random() + 1));
    if (SV > target.DP) {
      target.HP -= SV;
    }

    if (target.HP <= 0) {
      target.die(map);
      this.levelUp();
      return true;
    }
    target.NPCStrike(this);
    if (this.HP <= 0) {
      this.die(map);
    }
    return target == null;
  }

  public void levelUp() {
    this.LVL++;
    this.MaxHP += (this.LVL) * (int) (6.0 * Math.random() + 1);
    this.DP += (this.LVL) * (int) (6.0 * Math.random() + 1);
    this.SP += (this.LVL) * (int) (6.0 * Math.random() + 1);
  }

  public void heroRegen() {
    int regen = new Random().nextInt(1, 11);
    switch (regen) {
      case 1:
        this.HP = this.MaxHP;

      case 2, 3, 4, 5:
          if (this.HP + this.HP * 0.3 > this.getMaxHP()) {
              this.HP = this.MaxHP;
          } else {
              this.HP += this.HP * 0.3;
          }

      case 6, 7, 8, 9, 10:
          if (this.HP + this.HP * 0.1 > this.getMaxHP()) {
              this.HP = this.MaxHP;
          } else {
              this.HP += this.HP * 0.1;
          }
    }
  }

  public BufferedImage getHeroDown() {
    return heroDown;
  }

  public BufferedImage getHeroUp() {
    return heroUp;
  }

  public BufferedImage getHeroLeft() {
    return heroLeft;
  }

  public BufferedImage getHeroRight() {
    return heroRight;
  }

  public int getHeroPositionX() {
    return heroPositionX;
  }

  public int getHeroPositionY() {
    return HeroPositionY;
  }

  public void setHeroPositionX(int heroPositionX) {
    this.heroPositionX = heroPositionX;
  }

  public void setHeroPositionY(int heroPositionY) {
    HeroPositionY = heroPositionY;
  }
}
