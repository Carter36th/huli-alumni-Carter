package com.wanderer.service.implementation;

import com.wanderer.model.User;
import com.wanderer.repositories.UserRepository;
import com.wanderer.service.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImp(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Optional<User> findUser(String username, String password) {
    return userRepository.findByUsernameAndPassword(username, password);
  }

  @Override
  public void addUser(User user) {
    userRepository.save(user);
  }

}
