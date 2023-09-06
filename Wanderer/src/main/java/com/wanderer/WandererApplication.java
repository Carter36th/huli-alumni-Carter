package com.wanderer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
public class WandererApplication {
	public static Board board;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(WandererApplication.class, args);
		System.setProperty("java.awt.headless", "false");
		board = new Board();
		if (!GraphicsEnvironment.isHeadless()) {
			JFrame frame = new JFrame("RPG Game");
			frame.add(board);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			frame.pack();
			frame.addKeyListener(board);
		} else {
			throw new Exception("Headless environment required.");
		}
			board.startGame();
	}
}
