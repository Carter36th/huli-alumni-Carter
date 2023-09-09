package com.wanderer.service.implementation;

import com.wanderer.model.User;
import com.wanderer.repositories.UserRepository;
import com.wanderer.service.UserService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

  private final UserRepository userRepository;

  public UserServiceImp(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void save(User user) {
    userRepository.save(user);
  }

  @Override
  public Optional<User> userExist(String nickname, String password) {
    return userRepository.findAll().stream()
        .filter(u -> u.getPassword().equals(password))
        .filter(u -> u.getNickName().equals(nickname))
        .findFirst();
  }

  @Override
  public Long addUser(User user) {
    userRepository.save(user);
    return user.getId();
  }
}
