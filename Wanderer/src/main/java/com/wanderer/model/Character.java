package com.wanderer.model;

import com.wanderer.service.implementation.MapGeneratorImp;

import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class Character {

  public int LVL = 1;
  protected int MaxHP;
  protected int HP = 1;
  protected int DP;
  protected int SP;
  public boolean isAlive = true;
  protected int charID;
  protected static int numOfNPCs = 4;
  private int charPositionX;
  private int charPositionY;

  protected BufferedImage image;

  int i = new Random().nextInt(10 - 1) + 1;
  int j = new Random().nextInt(10 - 1) + 1;

  public void NPCPositioning(MapGeneratorImp map) {
    if (isAlive) {
      int plusMinus = new Random().nextInt(10 - 1) + 1;
      if (plusMinus < 3 && j < 9 && map.mapArr[j + 1][i] == 0) {
        map.mapArr[j][i] = 0;
        j++;
      } else if (plusMinus >= 3 && plusMinus < 5 && j > 0 && map.mapArr[j - 1][i] == 0) {
        map.mapArr[j][i] = 0;
        j--;
      } else if (plusMinus >= 5 && plusMinus < 7 && i < 9 && map.mapArr[j][i + 1] == 0) {
        map.mapArr[j][i] = 0;
        i++;
      } else if (plusMinus >= 7 && i > 0 && map.mapArr[j][i - 1] == 0) {
        map.mapArr[j][i] = 0;
        i--;
      }
    }
    if (map.mapArr[j][i] == 0) {
      charPositionX = j * 72;
      charPositionY = i * 72;
      map.mapArr[j][i] = charID;
    }
  }

  public void spawnNPC(MapGeneratorImp map) {
    i = new Random().nextInt(10 - 1) + 1;
    j = new Random().nextInt(10 - 1) + 1;
    if (map.mapArr[j][i] == 0) {
      charPositionX = j * 72;
      charPositionY = i * 72;
      map.mapArr[j][i] = charID;
    } else {
      spawnNPC(map);
    }
  }

  public void battle(Character hero) {

  }

  public void die(MapGeneratorImp map) {
    isAlive = false;
    map.mapArr[charPositionX / 72][charPositionY / 72] = 0;
  }

  public void NPCStrike(Character hero) {
    int SV = this.SP + (2 * (int) (6.0 * Math.random() + 1));
    if (SV > hero.DP) {
      hero.HP -= SV;
    }
  }

  public void NPCLvlUp() {

  }

  public int getLVL() {
    return LVL;
  }

  public int getMaxHP() {
    return MaxHP;
  }

  public int getHP() {
    return HP;
  }

  public int getDP() {
    return DP;
  }

  public int getSP() {
    return SP;
  }

  public boolean isAlive() {
    return isAlive;
  }

  public int getCharPositionX() {
    return charPositionX;
  }

  public int getCharPositionY() {
    return charPositionY;
  }

  public BufferedImage getImage() {
    return image;
  }

  @Override
  public String toString() {
    return "Character{" +
        "LVL=" + LVL +
        ", MaxHP=" + MaxHP +
        ", HP=" + HP +
        ", DP=" + DP +
        ", SP=" + SP +
        ", isAlive=" + isAlive +
        ", charID=" + charID +
        ", charPositionX=" + charPositionX +
        ", charPositionY=" + charPositionY +
        ", i=" + i +
        ", j=" + j +
        '}';
  }
}
