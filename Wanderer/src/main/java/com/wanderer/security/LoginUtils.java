package com.wanderer.security;

import com.wanderer.model.User;
import com.wanderer.security.jwt.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LoginUtils {

  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtUtils;

  public LoginUtils(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
    this.authenticationManager = authenticationManager;
    this.jwtUtils = jwtUtils;
  }

  public String authenticate(User user) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    return jwtUtils.generateJwtToken(authentication);
  }

}
