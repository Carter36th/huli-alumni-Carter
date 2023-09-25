package com.wanderer.service.implementation;

import com.wanderer.model.User;
import com.wanderer.repositories.UserRepository;
import com.wanderer.security.LoginUtils;
import com.wanderer.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

  private final UserRepository userRepository;
  private final LoginUtils loginUtils;

  @Autowired
  public UserServiceImp(UserRepository userRepository, LoginUtils loginUtils) {
    this.userRepository = userRepository;
    this.loginUtils = loginUtils;
  }

  @Override
  public Optional<User> findUser(String username, String password) {
    return userRepository.findByUsernameAndPassword(username, password);
  }

  @Override
  public void addUser(User user) {
    userRepository.save(user);
  }

  @Override
  public ResponseEntity<?> loginREST(String username, String password) {
    Optional<User> user = findUser(username, password);
    if (user.isPresent()) {
      String token = loginUtils.authenticate(user.get());
      return ResponseEntity.ok().body(token);
    } else {
      return ResponseEntity.status(404).body("Not found");
    }
  }

  @Override
  public String login(String username, String password, HttpServletResponse response) {
    Optional<User> user = findUser(username, password);
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

}
