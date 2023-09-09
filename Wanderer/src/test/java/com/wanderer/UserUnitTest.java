package com.wanderer;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

import com.wanderer.model.User;
import com.wanderer.repositories.UserRepository;
import com.wanderer.service.UserService;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserUnitTest {

  @Mock
  private UserRepository userRepository;
  @Mock
  private UserService userService;
  private User user;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    user = new User(1L, "Jan", "Loucka", "Jan", "1234", new Date());
    userRepository.save(user);
  }

  @Test
  public void test_DB_connection() {
    when(userRepository.findByNicknameAndPassword(user.getNickname(), user.getPassword())).thenReturn(Optional.of(user));

    assertTrue(userRepository.findByNicknameAndPassword(user.getNickname(), user.getPassword()).isPresent());

    verify(userRepository, times(1)).findByNicknameAndPassword(user.getNickname(), user.getPassword());
    assertEquals("result", user.getNickname(), "Jan");

  }

  @Test
  public void test_find_user() {
    Optional<User> result = userService.findUser(user.getNickname(), user.getPassword());

    assertTrue(result.isPresent());

    assertEquals("result", user, result.get());
  }

  @Test
  public void test_add_user(){
    when(userRepository.save(user)).thenReturn(user);
    Long result = userService.addUser(user);
    assertEquals("result", 1L, result);
  }

}
