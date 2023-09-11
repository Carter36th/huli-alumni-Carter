package com.wanderer.repositories;

import com.wanderer.model.User;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsernameAndPassword(String nickname, String password);
  @NotNull
  Optional<User> findById(@NotNull Long id);

  Optional<User> findFirstByUsername(String username);

}
