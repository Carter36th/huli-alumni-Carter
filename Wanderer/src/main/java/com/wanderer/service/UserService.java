package com.wanderer.service;

import com.wanderer.model.User;
import java.util.Optional;

public interface UserService {
  Optional<User> findUser(String nickname, String password);
  Long addUser(User user);

}
