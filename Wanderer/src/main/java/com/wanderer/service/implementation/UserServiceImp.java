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
  public Optional<User> findUser(String nickname, String password) {
    return userRepository.findByNicknameAndPassword(nickname, password);
  }

  @Override
  public Long addUser(User user) {
    userRepository.save(user);
    return user.getId();
  }
}
