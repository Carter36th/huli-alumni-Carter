package com.wanderer.Controllers;

import com.wanderer.Board;
import com.wanderer.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

  private final Board board;
  private final UserService userService;

  public MainController(Board board, UserService userService) {
    this.board = board;
    this.userService = userService;
  }

  @GetMapping("/wanderer")
  public String wanderer() {
    return "redirect:/login";
  }

  @PostMapping("/play")
  public String play() throws InterruptedException {
    board.startGame();
    return "wanderer";
  }

}
