package com.wanderer.service;

import com.wanderer.model.User;
import java.util.Optional;

public interface UserService {
  Optional<User> findUser(String username, String password);
  void addUser(User user);

}
