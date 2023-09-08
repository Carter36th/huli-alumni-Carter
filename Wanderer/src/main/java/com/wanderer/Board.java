package com.wanderer;

import com.wanderer.service.implementation.MapGeneratorImp;
import com.wanderer.model.Boss;
import com.wanderer.model.Hero;
import com.wanderer.model.Skeleton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Board extends JComponent implements KeyListener {

  private static BufferedImage currentSide;
  private final static Hero hero = new Hero();
  private final static Boss boss = new Boss();
  private final static Skeleton skeleton1 = new Skeleton();
  private final static Skeleton skeleton2 = new Skeleton();
  private final static Skeleton skeleton3 = new Skeleton();
  private final MapGeneratorImp map = new MapGeneratorImp();
  static final int TILE_SIZE = 72;
  private final List<Skeleton> skeletonList = new ArrayList<>(
      List.of(skeleton1, skeleton2, skeleton3));
  private boolean inCombat = false;

  public Board() {
    currentSide = hero.getHeroDown();
    setPreferredSize(new Dimension(720, 746));
    setVisible(true);
    for (Skeleton temp : skeletonList) {
      temp.spawnNPC(map);
    }
    boss.spawnNPC(map);
  }

  public void recalculateNPCPositions() {

    for (Skeleton temp : skeletonList) {
      if (temp.isAlive()) {
        temp.NPCPositioning(map);
      }
    }
    if (boss.isAlive()) {
      boss.NPCPositioning(map);
    }
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    map.drawMap(graphics);

    for (Skeleton temp : skeletonList) {
      if (temp.isAlive()) {
        graphics.drawImage(temp.getImage(), temp.getCharPositionX(), temp.getCharPositionY(), null);
      }
    }

    if (boss.isAlive()) {
      graphics.drawImage(boss.getImage(), boss.getCharPositionX(), boss.getCharPositionY(), null);
    }

    String infoPannel =
        "Hero  (Level  " + hero.getLVL() + ")  HP:  " + hero.getHP() + "/" + hero.getMaxHP()
            + "  |  DP:  " + hero.getDP() + "  |  SP:  " + hero.getSP();
    Font infoFont = new Font(infoPannel, Font.BOLD, 30);
    graphics.setFont(infoFont);
    graphics.drawString(infoPannel, 35, 744);

    if (hero.isAlive()) {
      graphics.drawImage(currentSide, hero.getHeroPositionX(), hero.getHeroPositionY(), null);
    }
  }

  public void startGame() throws InterruptedException {
    while (true) {
      SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
          if (!inCombat) {
            recalculateNPCPositions();
            repaint();
          }
        }
      });
      Thread.sleep(900);
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (hero.isAlive() && !inCombat) {
      if (e.getKeyCode() == KeyEvent.VK_UP) {
        currentSide = hero.getHeroUp();
        if (hero.getHeroPositionY() > 0 && collisionChecker(e)) {
          hero.setHeroPositionY(hero.getHeroPositionY() - TILE_SIZE);
        }
      } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
        currentSide = hero.getHeroDown();
        if (hero.getHeroPositionY() < 648 && collisionChecker(e)) {
          hero.setHeroPositionY(hero.getHeroPositionY() + TILE_SIZE);
        }
      } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        currentSide = hero.getHeroLeft();
        if (hero.getHeroPositionX() > 0 && collisionChecker(e)) {
          hero.setHeroPositionX(hero.getHeroPositionX() - TILE_SIZE);
        }
      } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        currentSide = hero.getHeroRight();
        if (hero.getHeroPositionX() < 648 && collisionChecker(e)) {
          hero.setHeroPositionX(hero.getHeroPositionX() + TILE_SIZE);
        }
      }

      switch (map.mapArr[hero.getHeroPositionX() / TILE_SIZE][hero.getHeroPositionY()
          / TILE_SIZE]) {
        case 5 -> {
          hero.battle(skeleton1);
          inCombat = true;
        }
        case 6 -> {
          hero.battle(skeleton2);
          inCombat = true;
        }
        case 7 -> {
          hero.battle(skeleton3);
          inCombat = true;
        }
        case 4 -> {
          hero.battle(boss);
          inCombat = true;
        }
      }
    } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      inCombat = !hero.HeroStrike(map);
    }
    if (!boss.isAlive()) {
      map.mapLvlUp();
      map.fillMap();
      boss.isAlive = true;
      boss.NPCLvlUp();
      boss.spawnNPC(map);

      for (Skeleton temp : skeletonList) {
        temp.isAlive = true;
        temp.NPCLvlUp();
        temp.spawnNPC(map);
      }
      hero.setHeroPositionX(0);
      hero.setHeroPositionY(0);
      hero.heroRegen();
    }
    repaint();
  }

  public boolean collisionChecker(KeyEvent e) {

    if (e.getKeyCode() == KeyEvent.VK_UP && map.getCurrentPositionY() > 0) {
      map.decreseMapArrY();
      if (map.getMapArrValue() != 1) {
        return true;
      } else {
        map.increaseMapArrY();
      }
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN && map.getCurrentPositionY() < 10) {
      map.increaseMapArrY();
      if (map.getMapArrValue() != 1) {
        return true;
      } else {
        map.decreseMapArrY();
      }
    } else if (e.getKeyCode() == KeyEvent.VK_LEFT && map.getCurrentPositionX() > 0) {
      map.decreseMapArrX();
      if (map.getMapArrValue() != 1) {
        return true;
      } else {
        map.increaseMapArrX();
      }
    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && map.getCurrentPositionX() < 10) {
      map.increaseMapArrX();
      if (map.getMapArrValue() != 1) {
        return true;
      } else {
        map.decreseMapArrX();
      }
    }
    return false;
  }
}
