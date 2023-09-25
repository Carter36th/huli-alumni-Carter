package com.wanderer.Controllers.RESTControllers;

import com.wanderer.model.User;
import com.wanderer.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTUserController {

  private final UserService userService;

  public RESTUserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/api/login")
  public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
    return userService.loginREST(username, password);
  }

  @PostMapping("/api/register")
  public ResponseEntity<?> registerNew(@ModelAttribute User user) {
    userService.addUser(user);
    return ResponseEntity.ok().body("User registered");
  }

}
