package com.wanderer.Controllers.RESTControllers;

import com.wanderer.WandererApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTMainController {

  @GetMapping("/api/play")
  public void play() throws Exception {
    WandererApplication.playWanderer();
  }
}
