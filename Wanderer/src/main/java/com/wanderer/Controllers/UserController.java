package com.wanderer.Controllers;

import com.wanderer.model.User;
import com.wanderer.service.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/login")
  public String loginPage() {
    return "login";
  }

  @PostMapping("/login")
  public String login(@RequestParam String nickname, @RequestParam String password) {
    Optional<User> user = userService.userExist(nickname, password);
    if (user.isPresent()) {
      Long userId = user.get().getId();
      return "redirect:/wanderer/"+userId;
    } return "redirect:/register";
  }

  @GetMapping("/register")
  public String registerPage(){
    return "register";
  }

  @PostMapping("/register")
  public String registerNew(@ModelAttribute User user) {
    Long userId = userService.addUser(user);
    return "redirect:/wanderer/"+userId;
  }
}
