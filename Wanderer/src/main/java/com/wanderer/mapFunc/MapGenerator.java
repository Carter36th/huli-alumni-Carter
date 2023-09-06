package com.wanderer.mapFunc;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MapGenerator {
    public int[][] mapArr = new int[10][10];
    public int currentPositionX = 0;
    public int currentPositionY = 0;
    int mapLvl = 1;
    Path path = Paths.get("D:\\Coding\\GreenFox\\huli-Wanderer\\huli-alumni-Carter\\Wanderer\\src\\main\\resources\\maps\\map" + mapLvl + ".txt");
    public MapGenerator() {
        fillMap();
    }

    public void fillMap() {
        path = Paths.get("D:\\Coding\\GreenFox\\huli-Wanderer\\huli-alumni-Carter\\Wanderer\\src\\main\\resources\\maps\\map" + mapLvl + ".txt");
        List<String> mapList;
        try {
            mapList = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                char charToInt = mapList.get(j).charAt(i);
                int tempChar = Character.getNumericValue(charToInt);
                mapArr[j][i] = tempChar;
            }
        }
    }

    public void drawMap(Graphics graphics) {

        int countI = 0;
        int countJ = 0;
        for (int i = 0; i < 720; i += 72) {
            for (int j = 0; j < 720; j += 72) {
                if (mapArr[countI][countJ] == 1) {
                    PositionedImage image = new PositionedImage("D:\\Coding\\GreenFox\\huli-Wanderer\\huli-alumni-Carter\\Wanderer\\src\\main\\resources\\png\\wall.png", i, j);
                    image.draw(graphics);
                } else {
                    PositionedImage image = new PositionedImage("D:\\Coding\\GreenFox\\huli-Wanderer\\huli-alumni-Carter\\Wanderer\\src\\main\\resources\\png\\floor.png", i, j);
                    image.draw(graphics);
                }
                countJ++;
            }
            countI++;
            countJ = 0;
        }
    }

    public void mapLvlUp() {
        mapLvl++;
        currentPositionY = 0;
        currentPositionX = 0;
    }

    public int getMapArrValue() {
        return mapArr[currentPositionX][currentPositionY];
    }

    public void decreseMapArrY() {
        currentPositionY--;
    }

    public void increaseMapArrY() {
        currentPositionY++;
    }

    public void increaseMapArrX() {
        currentPositionX++;
    }

    public void decreseMapArrX() {
        currentPositionX--;
    }

    public int getCurrentPositionX() {
        return currentPositionX;
    }

    public int getCurrentPositionY() {
        return currentPositionY;
    }
}
