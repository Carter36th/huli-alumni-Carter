package com.wanderer.security.services;

import com.wanderer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "userDetailsService")
public class UserDetailsServiceImp implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public UserDetailsServiceImp(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    com.wanderer.model.User user = userRepository.findFirstByUsername(username).orElseThrow(
        () -> new UsernameNotFoundException("User not found with username: " + username));
    return User.withUsername(user.getUsername())
        .password(user.getPassword())
        .roles("USER")
        .build();
  }
}
