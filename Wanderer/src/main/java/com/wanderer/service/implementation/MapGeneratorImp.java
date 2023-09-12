package com.wanderer.service.implementation;

import com.wanderer.PositionedImage;
import com.wanderer.service.MapGenerator;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class MapGeneratorImp implements MapGenerator {

  public int[][] mapArr = new int[10][10];
  private int currentPositionX = 0;
  private int currentPositionY = 0;
  private int mapLvl = 1;


  @Value("${RESOURCES_PATH}")
  private String pathVariable;

  public MapGeneratorImp() {
    fillMap();
  }

  @Override
  public void fillMap() {
    Path path = Paths.get(
        pathVariable + "\\maps\\map"
            + mapLvl + ".txt");
    List<String> mapList;
    try {
      mapList = Files.readAllLines(path);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        char charToInt = mapList.get(j).charAt(i);
        mapArr[j][i] = Character.getNumericValue(charToInt);
      }
    }
  }

  @Override
  public void drawMap(Graphics graphics) {

    int countI = 0;
    int countJ = 0;
    for (int i = 0; i < 720; i += 72) {
      for (int j = 0; j < 720; j += 72) {
        PositionedImage image;
        if (mapArr[countI][countJ] == 1) {
          image = new PositionedImage(
              pathVariable + "\\png\\wall.png",
              i, j);
        } else {
          image = new PositionedImage(
              pathVariable + "\\png\\floor.png",
              i, j);
        }
        image.draw(graphics);
        countJ++;
      }
      countI++;
      countJ = 0;
    }
  }

  @Override
  public void mapLvlUp() {
    mapLvl++;
    currentPositionY = 0;
    currentPositionX = 0;
  }

  @Override
  public int getMapArrValue() {
    return mapArr[currentPositionX][currentPositionY];
  }

  @Override
  public void decreseMapArrY() {
    currentPositionY--;
  }

  @Override
  public void increaseMapArrY() {
    currentPositionY++;
  }

  @Override
  public void increaseMapArrX() {
    currentPositionX++;
  }

  @Override
  public void decreseMapArrX() {
    currentPositionX--;
  }

  @Override
  public int getCurrentPositionX() {
    return currentPositionX;
  }

  @Override
  public int getCurrentPositionY() {
    return currentPositionY;
  }
}
