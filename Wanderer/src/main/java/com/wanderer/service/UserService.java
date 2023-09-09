package com.wanderer.service;

import com.wanderer.model.User;
import java.util.Optional;

public interface UserService {
  void save(User user);
  Optional<User> userExist(String nickname, String password);
  Long addUser(User user);

}
