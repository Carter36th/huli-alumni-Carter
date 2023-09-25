package com.wanderer.service;

import com.wanderer.model.User;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

public interface UserService {

  Optional<User> findUser(String username, String password);

  void addUser(User user);

  ResponseEntity<?> loginREST(String username, String password);

  String login(String username, String password, HttpServletResponse response);

}
