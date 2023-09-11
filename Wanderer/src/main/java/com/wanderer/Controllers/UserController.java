package com.wanderer.Controllers;

import com.wanderer.model.User;
import com.wanderer.security.LoginUtils;
import com.wanderer.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
  private final LoginUtils loginUtils;

  @Autowired
  public UserController(UserService userService, LoginUtils loginUtils) {
    this.userService = userService;
    this.loginUtils = loginUtils;
  }

  @GetMapping("/login")
  public String loginPage() {
    return "login";
  }

  @PostMapping("/login")
  public String login(@RequestParam String username, @RequestParam String password,
      HttpServletResponse response) {
    Optional<User> user = userService.findUser(username, password);
    if (user.isPresent()) {
      String token = loginUtils.authenticate(user.get());

      Cookie cookie = new Cookie("JWT_TOKEN", token);
      cookie.setPath("/");
      cookie.setMaxAge(86400000);
      response.addCookie(cookie);

      return "redirect:/wanderer";
    }
    return "redirect:/register";
  }

  @GetMapping("/register")
  public String registerPage() {
    return "register";
  }

  @PostMapping("/register")
  public String registerNew(@ModelAttribute User user) {
    userService.addUser(user);
    return "redirect:/wanderer";
  }
}
