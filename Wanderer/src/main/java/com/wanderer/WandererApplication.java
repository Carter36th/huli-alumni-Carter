package com.wanderer;

import java.io.FileOutputStream;
import java.util.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class WandererApplication {

  public static Board board;

  public static void main(String[] args) {
    System.setProperty("java.awt.headless", "false");
    SpringApplication.run(WandererApplication.class, args);
    board = new Board();
  }

  public static void playWanderer() throws Exception {
    if (!GraphicsEnvironment.isHeadless()) {
      JFrame frame = new JFrame("RPG Game");
      frame.add(board);
//      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.pack();
      frame.addKeyListener(board);
    } else {
      throw new Exception("Headless environment required.");
    }
    board.startGame();
  }
}
