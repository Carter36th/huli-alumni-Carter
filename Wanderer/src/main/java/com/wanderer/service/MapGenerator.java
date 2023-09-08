package com.wanderer.service;

import java.awt.Graphics;

public interface MapGenerator {

  void fillMap();

  void drawMap(Graphics graphics);

  void mapLvlUp();

  int getMapArrValue();

  void decreseMapArrY();

  void increaseMapArrY();

  void increaseMapArrX();

  void decreseMapArrX();

  int getCurrentPositionX();

  int getCurrentPositionY();
}
