package com.wanderer.Controllers;

import com.wanderer.WandererApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

  @GetMapping("/wanderer")
  public String wanderer() {
    return "redirect:/login";
  }

  @PostMapping("/play")
  public void play() throws Exception {
    WandererApplication.playWanderer();
  }

}
