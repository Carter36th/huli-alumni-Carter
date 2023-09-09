package com.wanderer.Controllers;

import com.wanderer.Board;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

  private final Board board;

  public MainController(Board board) {
    this.board = board;
  }

  @GetMapping("/wanderer/{userId}")
  public String wanderer(@PathVariable() Long userId) {
    return "wanderer";
  }

  @PostMapping("/play")
  public String play() throws InterruptedException {
    board.startGame();
    return "wanderer";
  }

}
